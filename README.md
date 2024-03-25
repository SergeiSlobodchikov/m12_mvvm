### Этот код представляет собой пример создания Android-приложения с использованием Kotlin и MVVM-паттерна. 

В классе MainActivity создается и отображается фрагмент MainFragment. Если это первый запуск приложения, то фрагмент заменяется на новый экземпляр MainFragment.

В классе [MainRepository](https://github.com/SergeiSlobodchikov/m12_mvvm/blob/main/app/src/main/java/com/example/m12_mvvm/MainRepository.kt) определена функция search, которая принимает строку поиска и возвращает ее через 5 секунд.

В классе [MainViewModelFactory](https://github.com/SergeiSlobodchikov/m12_mvvm/blob/main/app/src/main/java/com/example/m12_mvvm/MainViewModelFactory.kt) создается экземпляр MainViewModel, который использует MainRepository для выполнения поиска.

В классе [State](https://github.com/SergeiSlobodchikov/m12_mvvm/blob/main/app/src/main/java/com/example/m12_mvvm/State.kt) определены три состояния: Idle, Loading и Finish. Состояние Finish содержит результат поиска.

В классе [MainFragment](https://github.com/SergeiSlobodchikov/m12_mvvm/blob/main/app/src/main/java/com/example/m12_mvvm/ui.main/MainFragment.kt) определены методы для обработки событий ввода пользователя и обновления состояния приложения.

Класс [MainViewModel](https://github.com/SergeiSlobodchikov/m12_mvvm/blob/main/app/src/main/java/com/example/m12_mvvm/ui.main/MainViewModel.kt), который является частью MVVM-паттерна. Он содержит логику, связанную с управлением состоянием приложения и взаимодействием с другими компонентами приложения. определены следующие функции:
- onEditTextChanged - эта функция вызывается, когда текст в поле ввода изменяется. Она отправляет значение, указывающее, что кнопка поиска должна быть видимой, если введенный текст имеет длину не менее 3 символов.
- onSearchButtonClick - эта функция вызывается, когда пользователь нажимает кнопку поиска. Она меняет состояние приложения на State.Loading, затем выполняет поиск в MainRepository и меняет состояние на State.Finish с результатом поиска.
