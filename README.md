Микросервис для обработки транзакции и лимитов. Из двух API:
    1.ClientRequestController - для обработки запросов клиента,
    2.TransactionController - для обработки запросов банковского сервера.
****
RestControlers(Контроллеры):
    Документация в файле Swagger_Controllers.pdf
****
Services(Сервисы):
    ****
    1.CurrencyService:
        1.public List<Currency> sendDailyRequest() - каждый день в полночь автоматический отправляет запрос на биржу для получения курса валют(последнее закрытие);
    ****
    2.LimitService:
        1.public Limit updateLimit(Limit oldLimit) - обновляет существующий лимит(но остаток лимита не обновляется). В сущности Limit обязателен поля:
            currentLimit - новый лимит,
            accountNumber - номер аккаунта лимит который хотите обновить,
            category - категория лимита( В базе есть две категории { "id" : 1, "name" : "product" }, { "id" : 2, "name" : "service" }).
        2.public List<Limit> updateMonthLimitSum() - каждый месяц 1 го числа автоматический обновляет лимит, также обновляется остаток лимита!(устанавливается последний                 установленный лимит).
    ***
    3.TransactionService:
        1.public Transaction transaction(Transaction transaction) - сохраняет информацию о транзакции в Базе Данных.(исходя из суммы и валюты транзакции рассчитывает остаток             лимита, также проверяет превышен ли лимит).
        2.public List<Transaction> getAllExceededTransactions(Long accountNumber) - по номеру аккаунта(счета) возвращает список транзакции превысившие лимит.
