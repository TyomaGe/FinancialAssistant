package com.github.FinancialAssistant.msg;

import com.github.FinancialAssistant.functional.AttachedButtons;
import com.github.FinancialAssistant.functional.Keyboard;

public class Constants {
    public static final Message INVALID_INPUT = new Message("\uD83D\uDE28 Вы неверно указали значения. Нужный формат ввода:\n\n❗ НАЗВАНИЕ   ЧИСЛОВОЕ_ЗНАЧЕНИЕ");
    ;
    private final static Keyboard keyboard = new Keyboard();
    private final static AttachedButtons attachedButtons = new AttachedButtons();

    public final static Message EXP_SUM = new Message("\uD83D\uDCB3 Впишите потраченную сумму в рублях");
    public final static Message INCOME_SUM = new Message("\uD83D\uDCB3 Впишите сумму в рублях");
    public final static Message INVALID_SUM = new Message("Некорректный формат ввода, проверьте:\n\n" +
            "\uD83D\uDFE9 Положительное ли число вы вводите \n" +
            "\uD83D\uDCDD Соответствует ли число формату ввода:\n\n❗ ПОЛОЖИТЕЛЬНОЕ ЧИСЛО МЕНЕЕ 10 ТРИЛЛИОНОВ\n❗ ЧИСЛО ЗНАКОВ ПОСЛЕ ЗАПЯТОЙ НЕ БОЛЕЕ ДВУХ\n\n✅Примеры ввода:     1234.56    1500    255.5");
    public final static Message UNK_COM = new Message("Извини, такую команду я не знаю\uD83E\uDD14. Напиши /help, чтобы увидеть полный список команд");
    public final static Message TOO_LONG_CAT = new Message("Название не должно превышать 45 символов\uD83E\uDD13");
    public final static Message EMPTY_RESULT = new Message("За этот период не было записано ни одной траты\uD83E\uDD74");
    public final static Message NO_INCOMES = new Message("На данный момент у вас не указан ни один источник дохода\uD83E\uDEE1");
    public final static Message NO_DATA = new Message("За данный период не найдено ни одной записи\uD83D\uDC7B");
    public final static Message USR_CAT = new Message("""
            Напиши название своей категории расходов и сумму, которую хочешь записать🤩
            
            ✅Пример ввода:  Подарок маме 1599.99
            """);
    public final static Message USR_INCOME = new Message("""
            Напиши название своей категории доходов и сумму, которую хочешь записать🤩
            
            ✅Пример ввода:  Помощь от папы 2599.99
            """);
    public final static Message HELP_COM = new Message("""
            Функционал бота😁\s
            
            /start - начинает работу с ботом\s
            /help - выводит список доступных команд
            /register - регистрирует пользователя
            
            Пользуйтесь КЛАВИАТУРОЙ снизу, это очень удобно❗""");

    public final static Message INV_PERIOD = new Message("Что-то пошло не так\uD83D\uDE15\nВозможно ты указал неверный формат или неправильную дату");
    public final static Message ASK_FOR_REG = new Message("\uD83D\uDE15 Ты должен зарегистрироваться, чтобы использовать эту команду",
            attachedButtons.createButtonsForRegistration());
    public final static Message ALR_REG = new Message("Ты уже зарегистрирован, можешь продолжить свою работу\uD83D\uDC40");
    public final static Message NOW_REG = new Message("\uD83E\uDD42 Поздравляю! Теперь, ты можешь пользоваться всеми моими полезными штуками!\uD83C\uDF8A");
    public final static Message START_TEXT_TEMPL = new Message("Приветствую тебя, мой друг\uD83D\uDC4B\uD83C\uDFFB." +
            " Перед началом пользования прошу тебя зарегистрироваться. Для этого напиши команду /register или нажми на кнопку снизу\uD83D\uDC47\uD83C\uDFFB",
            attachedButtons.createButtonsForRegistration());
    public final static String START = "/start";
    public final static String SET_EXP = "\uD83D\uDCC9Записать расходы";
    public final static String SEND_EXP = "\uD83D\uDCB8Вывести список расходов";
    public final static String SET_INCOME = "\uD83D\uDCC8Записать доходы";
    public final static String SEND_INCOME = "\uD83D\uDCB0Вывести список доходов";
    public final static String COMPARE = "\uD83D\uDCCAФинансовый отчет";
    public final static String GOOD_REPORT = "Похоже ваши дела идут достаточно хорошо\uD83D\uDE0E";
    public final static String BAD_REPORT = "Вам явно стоит следить за тем, куда вы тратите деньги\uD83D\uDE2D";
    public final static String NOT_BAD_REPORT = "Идеальный баланс. Возможно, вам стоит задуматься о том, как сэкономить побольше денег\uD83D\uDE42";
    public final static Message ASK_PERIOD_EXP = new Message("""
            Укажите, каким образом вы хотите записать интервал времени📆""", attachedButtons.createButtonsForPeriodFormat(true, false));
    public final static Message ASK_PERIOD_INCOME = new Message("""
            Укажите, каким образом вы хотите записать интервал времени📆""", attachedButtons.createButtonsForPeriodFormat(false, true));
    public final static Message ASK_PERIOD_TOTAL = new Message("""
            Укажите, каким образом вы хотите записать интервал времени📆""", attachedButtons.createButtonsForPeriodFormat(false, false));
    public final static Message MONTH_PATTERN = new Message("""
            📅Запишите месяц в формате:\n\nгггг-мм\n\n✅Пример ввода:  2024-12""");
    public final static Message PERIOD_PATTERN = new Message("""
            🗓️Укажите период, за который вы хотите получить статистику\nХронологический порядок выставится автоматически:\n\nгггг-мм-дд    гггг-мм-дд\n\n✅Пример ввода:  2024-12-03 2024-12-22""");
    public final static Message EXP_LIST = new Message("Выбери название категории, которую ты хочешь указать⬇\uFE0F", attachedButtons.createButtonsForExpenses());
    public final static Message INCOME_LIST = new Message("⬇\uFE0FВыбери источник дохода\n❕При повторном указании текущее значение изменится на новое.\n❕При указании нуля, статья дохода будет удалена", attachedButtons.createButtonsForIncome());
}
