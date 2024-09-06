## Задание 1:
Создать простейшее окно управления сервером (по сути, любым),
содержащее две кнопки (JButton) – запустить сервер и остановить сервер.
Кнопки должны просто логировать нажатие (имитировать запуск и
остановку сервера, соответственно) и выставлять внутри интерфейса
соответствующее булево isServerWorking. 

Добавить на окно компонент JtextArea и выводить
сообщения сервера в него, а не в терминал.

Создать окно клиента чата. Окно должно содержать JtextField
для ввода логина, пароля, IP-адреса сервера, порта подключения
к серверу, область ввода сообщений, JTextArea область просмотра
сообщений чата и JButton подключения к серверу и отправки сообщения
в чат. Желательно сразу сгруппировать компоненты, относящиеся
к серверу сгруппировать на JPanel сверху экрана, а компоненты,
относящиеся к отправке сообщения – на JPanel снизу

Добавить на экран компонент JList – имитацию списка пользователей, заполнить этот список
несколькими выдуманными именами пользователей чата. Подсказка: компонент не может добавлять
или убирать элементы списка, он работает с методом setListData(), изучите его аргументы