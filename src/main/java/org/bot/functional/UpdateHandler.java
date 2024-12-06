package org.bot.functional;

import org.bot.database.ConstantDB;
import org.bot.database.DatabaseInitializer;
import org.bot.msg.Constants;
import org.bot.msg.Message;
import org.bot.msg.MessageSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class UpdateHandler {

    private final Map<Long, String> userStates = new HashMap<>();
    private final Map<Long, String> buttonInfoState = new HashMap<>();
    private final MessageSender messageSender;
    private final DatabaseInitializer dbHandler;

    public UpdateHandler(TelegramLongPollingBot bot) {
        messageSender = new MessageSender(bot);
        dbHandler = new DatabaseInitializer();
    }

    public void handleUpdate(Update update) throws SQLException {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatID = update.getMessage().getChatId();
            String sourceText = update.getMessage().getText();
            if (userStates.containsKey(chatID)) {
                handleUserStates(chatID, sourceText);
                return;
            }
            handleCommand(chatID, sourceText, update);
        } else if (update.hasCallbackQuery()) {
            long chatID = update.getCallbackQuery().getMessage().getChatId();
            String buttonInfo = update.getCallbackQuery().getData();
            handleCallbackQuery(chatID, buttonInfo);
        }
    }

    private void handleCommand(long chatID, String sourceText, Update update) throws SQLException {
        switch (sourceText) {
            case Constants.START:
                if (!dbHandler.checkIfSigned(chatID)) {
                    messageSender.send(chatID, Constants.START_TEXT_TEMPL);
                } else {
                    messageSender.send(chatID, Constants.ALR_REG);
                }
                break;
            case ConstantDB.USERS_COMMANDS:
                messageSender.send(chatID, Constants.HELP_COM);
                break;
            case ConstantDB.USERS_REGISTRATION:
                if (!dbHandler.checkIfSigned(chatID)) {
                    dbHandler.caseSignUpUsers(chatID, messageSender);
                } else {
                    messageSender.send(chatID, Constants.ALR_REG);
                }
                break;
            case Constants.SET_EXP:
                if (dbHandler.checkIfSigned(chatID)) {
                    messageSender.send(chatID, Constants.EXP_LIST);
                } else {
                    messageSender.send(chatID, Constants.ASK_FOR_REG);
                }
                break;
            case Constants.SEND_EXP:
                if (dbHandler.checkIfSigned(chatID)) {
                    messageSender.send(chatID, Constants.ASK_PERIOD);
                } else {
                    messageSender.send(chatID, Constants.ASK_FOR_REG);
                }
                break;
            default:
                messageSender.send(chatID, Constants.UNK_COM);
        }
    }

    private void handleUserStates(long chatID, String sourceText) throws SQLException {
        if (Objects.equals(userStates.get(chatID), ConstantDB.USERS_MONTH)) {
            int flag = 1;
            makeStatisticAboutExpenses(chatID, sourceText, flag);
        } else if (Objects.equals(userStates.get(chatID), ConstantDB.USERS_PERIOD)) {
            int flag = 2;
            makeStatisticAboutExpenses(chatID, sourceText, flag);
        }else{
            makeEntryAboutExpenses(chatID, sourceText);
        }
    }

    private void makeStatisticAboutExpenses(long chatID, String period, int flag) throws SQLException {
        userStates.remove(chatID);
        ArrayList<String> datesList = dbHandler.getDatabaseTools().parsePeriod(period, flag);
        if (datesList.isEmpty()) {
            messageSender.send(chatID, Constants.INV_PERIOD);
            return;
        }
        //dbHandler.getDatabaseTools().sendAllAmounts(chatID, messageSender, datesList);
        messageSender.send(chatID, new Message("Круто"));
    }

    private void handleCallbackQuery(long chatID, String buttonInfo) throws SQLException {
        if (Objects.equals(buttonInfo, ConstantDB.USERS_REGISTRATION)) {
            if (!dbHandler.checkIfSigned(chatID)) {
                dbHandler.caseSignUpUsers(chatID, messageSender);
            } else {
                messageSender.send(chatID, Constants.ALR_REG);
            }
        }else if (Objects.equals(buttonInfo, ConstantDB.USERS_COMMANDS)){
            messageSender.send(chatID, Constants.HELP_COM);
        } else if (Objects.equals(buttonInfo, ConstantDB.USERS_MONTH)){
            messageSender.send(chatID, Constants.MONTH_PATTERN);
            userStates.put(chatID, ConstantDB.USERS_MONTH);
        }else if (Objects.equals(buttonInfo, ConstantDB.USERS_PERIOD)) {
            messageSender.send(chatID, Constants.PERIOD_PATTERN);
            userStates.put(chatID, ConstantDB.USERS_PERIOD);
        }else {
            messageSender.send(chatID, Constants.EXP_SUM);
            userStates.put(chatID, buttonInfo);
            buttonInfoState.put(chatID, buttonInfo);
        }
    }

    private void makeEntryAboutExpenses(long chatID, String stringAmount) throws SQLException {
        String buttonInfo = buttonInfoState.get(chatID);
        userStates.remove(chatID);
        buttonInfoState.remove(chatID);
        float amount = dbHandler.getDatabaseTools().parseFloat(stringAmount);
        if (amount == -1) {
            messageSender.send(chatID, Constants.INVALID_SUM);
            return;
        }
        messageSender.send(chatID, new Message("Ваша сумма в " + amount + " рублей была успешно записана"));
        dbHandler.getDatabaseTools().inputEntry(chatID, buttonInfo, amount);
    }

}