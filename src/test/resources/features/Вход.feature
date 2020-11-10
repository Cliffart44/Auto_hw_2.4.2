#language:ru

Функциональность: Вход
  Структура сценария: Вход в личный кабинет
    Пусть совершен переход на страницу "Страница входа" по ссылке "http://localhost:9999"
    Когда в поле "Логин" введено значение "&lt;login&gt;"
    И в поле "Пароль" введено значение "&lt;password&gt;"
    И выполнено нажатие на кнопку "Продолжить"
    Тогда страница "Подтверждение входа" загрузилась
    Когда в поле "Код" введено значение "&lt;code&gt;"
    И выполнено нажатие на кнопку "Продолжить"
    Тогда страница "Дашбоард" загрузилась

    Примеры:
      | login | password  | code  |
      | vasya | qwerty123 | 99999 |

  Сценарий: Вход в личный кабинет (укороченный)
    Пусть пользователь залогинен с именем "vasya" и паролем "qwerty123";
    Когда он переводит "5 000" рублей с карты с номером "5559 0000 0000 0002" на свою "1" карту с главной страницы;
    Тогда баланс его "1" карты из списка на главной странице должен стать "15 000" рублей.