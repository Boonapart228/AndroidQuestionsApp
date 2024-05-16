package com.balan.androidquestionsapp.data.local


val juniorQuestion = """[
  {
    "title": "Що таке Jetpack Compose?",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Сучасний інструментарій для розробки Android UI",
        "isTrue": true
      },
      {
        "title": "Інструментарій для створення бібліотек",
        "isTrue": false
      },
      {
        "title": "Інтерфейс бази даних",
        "isTrue": false
      },
      {
        "title": "Плагін для збірки APK",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Composable-функції - це основний будівельний блок Compose.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Якою анотацією позначається Composable-функція?",
    "type": "radioButtons",
    "answers": [
      {
        "title": "@Composable",
        "isTrue": true
      },
      {
        "title": "@Annotation",
        "isTrue": false
      },
      {
        "title": "@ComposableFunction",
        "isTrue": false
      },
      {
        "title": "@Preview",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Основні стандартні елементи макету в Compose:",
    "type": "checkBox",
    "answers": [
      {
        "title": "Column",
        "isTrue": true
      },
      {
        "title": "Row",
        "isTrue": true
      },
      {
        "title": "Text",
        "isTrue": true
      },
      {
        "title": "Box",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Яка віконна панель використовується для імпорту, створення, керування та використання ресурсів у вашому додатку?",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Менеджер ресурсів",
        "isTrue": false
      },
      {
        "title": "Інструмент ресурсів",
        "isTrue": false
      },
      {
        "title": "Менеджер макетів",
        "isTrue": false
      },
      {
        "title": "Менеджер програм",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Який клас є автоматично генерованим класом Android, що містить ідентифікатори всіх ресурсів у проекті.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Клас ресурсів",
        "isTrue": false
      },
      {
        "title": "Клас ResourceID",
        "isTrue": false
      },
      {
        "title": "Клас Android",
        "isTrue": false
      },
      {
        "title": "Клас R",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Яка функція використовується для завантаження ресурсу зображення?",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Функція stringResource()",
        "isTrue": false
      },
      {
        "title": "Функція painterResource()",
        "isTrue": false
      },
      {
        "title": "Функція ImageResource()",
        "isTrue": false
      },
      {
        "title": "Функція loadResource()",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Який параметр функції використовується для додавання тексту доступності, що використовується TalkBack?",
    "type": "radioButtons",
    "answers": [
      {
        "title": "accessibilityText",
        "isTrue": false
      },
      {
        "title": "contentText",
        "isTrue": false
      },
      {
        "title": "accessibilityDescription",
        "isTrue": true
      },
      {
        "title": "contentDescription",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Макет Box розміщує елементи інтерфейсу користувача один на одному.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Який параметр використовується для вирівнювання дочірнього елемента до початку батьківського?",
    "type": "textField",
    "answers": [
      {
        "title": "Alignment.Start",
        "isTrue": true
      }
    ]
  }
]
""".trimIndent()

val middleQuestion = """[
  {
    "title": "З конкурентним програмуванням, код може виконуватися у порядку, відмінному від того, в якому він був написаний.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Які з наступних висловлювань є правдивими щодо контекстів корутин?",
    "type": "checkBox",
    "answers": [
      {
        "title": "Dispatchers.Default є найкраще для довгих завдань з читання та запису великих обсягів даних.",
        "isTrue": true
      },
      {
        "title": "Dispatchers.Main - для оновлення інтерфейсу, не для довгих завдань.",
        "isTrue": true
      },
      {
        "title": "Job керує життєвим циклом корутини.",
        "isTrue": true
      },
      {
        "title": "Dispatchers.IO оптимізований для мережевого вводу-виводу, серед інших фонових завдань.",
        "isTrue": true
      }
    ]
  },
  {
    "title": "launch() та async() є функціями розширення для ___, який відстежує всі корутини, які він створює.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "CoroutineScope",
        "isTrue": true
      },
      {
        "title": "Job",
        "isTrue": false
      },
      {
        "title": "Dispatcher",
        "isTrue": false
      },
      {
        "title": "CoroutineContext",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Які з наступних висловлювань є правдивими щодо структурованої конкурентності та її найкращих практик?",
    "type": "checkBox",
    "answers": [
      {
        "title": "Якщо скасовано головну корутину, дочірні також скасуються.",
        "isTrue": true
      },
      {
        "title": "Батьківська область може завершитися раніше, ніж її дочірні.",
        "isTrue": true
      },
      {
        "title": "Помилка повинна передаватися вниз без скасування батьківської корутини.",
        "isTrue": false
      },
      {
        "title": "Корутини повинні бути запущені з області видимості корутини.",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Які з наступних висловлювань є правдивими щодо веб-служб?",
    "type": "checkBox",
    "answers": [
      {
        "title": "GET, POST та DELETE - це всі приклади операцій HTTP.",
        "isTrue": true
      },
      {
        "title": "URL - це тип URI, але не всі URI є URL.",
        "isTrue": true
      },
      {
        "title": "RESTful служби завжди надають відформатовану XML відповідь.",
        "isTrue": false
      },
      {
        "title": "Retrofit - це стороння бібліотека для обробки JSON з веб-служби.",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Retrofit - це стороння бібліотека, яка дозволяє вашому додатку надсилати запити до ___ веб-служби.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "XML",
        "isTrue": false
      },
      {
        "title": "Socket",
        "isTrue": false
      },
      {
        "title": "RESTful",
        "isTrue": true
      },
      {
        "title": "JSON",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Рекомендований спосіб виконання мережевого запиту Retrofit - це за допомогою корутини, запущеної в viewModelScope.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Щоб дозволити вашому додатку встановлювати з'єднання з Інтернетом, додайте дозвіл 'android.permission.INTERNET' у файл ___ .",
    "type": "radioButtons",
    "answers": [
      {
        "title": "MainActivity",
        "isTrue": false
      },
      {
        "title": "build.gradle",
        "isTrue": false
      },
      {
        "title": "Android manifest",
        "isTrue": true
      },
      {
        "title": "ViewModel",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Процес перетворення результату JSON у використовувані дані, як це робиться за допомогою Gson, називається ___ JSON.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Серіалізація",
        "isTrue": false
      },
      {
        "title": "Кодування",
        "isTrue": false
      },
      {
        "title": "Перетворення",
        "isTrue": false
      },
      {
        "title": "Розбірка",
        "isTrue": true
      }
    ]
  }
]""".trimIndent()


val seniorQuestion = """[
  {
    "title": "Маршрут визначається за допомогою даних типу ___ .",
    "type": "radioButtons",
    "answers": [
      {
        "title": "@Composable function",
        "isTrue": false
      },
      {
        "title": "NavHost.Route",
        "isTrue": false
      },
      {
        "title": "String",
        "isTrue": true
      },
      {
        "title": "NavRoute",
        "isTrue": false
      }
    ]
  },
  {
    "title": "З NavHost ви повинні явно вказати початковий екран.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": false
      },
      {
        "title": "Ні",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Вважається найкращою практикою не передавати NavHostController до окремих композицій.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  },
  {
    "title": "___ є композицією, яка керує тим, який екран відображається на основі заданого маршруту.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "NavController",
        "isTrue": false
      },
      {
        "title": "NavHostController",
        "isTrue": false
      },
      {
        "title": "NavHost",
        "isTrue": true
      },
      {
        "title": "ComposableNavigator",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Функція composable() , викликана в NavHost, приймає які два параметри?",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Вміст призначення та маршрут",
        "isTrue": false
      },
      {
        "title": "Маршрут та вміст композиції",
        "isTrue": true
      },
      {
        "title": "Шлях та композиція",
        "isTrue": false
      },
      {
        "title": "Композиція та намір",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Ви можете змінити поточно відображений маршрут за допомогою методу ___ .",
    "type": "radioButtons",
    "answers": [
      {
        "title": "update()",
        "isTrue": false
      },
      {
        "title": "composable()",
        "isTrue": false
      },
      {
        "title": "transition()",
        "isTrue": false
      },
      {
        "title": "navigate()",
        "isTrue": true
      }
    ]
  },
  {
    "title": "Метод ___ видаляє один або кілька екранів зі стеку назад.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "popToStartDestination()",
        "isTrue": false
      },
      {
        "title": "popBackStack()",
        "isTrue": true
      },
      {
        "title": "popComposable()",
        "isTrue": false
      },
      {
        "title": "popToBackStack()",
        "isTrue": false
      }
    ]
  },
  {
    "title": "У багатоекранному додатку перехід на новий екран розміщує його внизу стеку назад.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  },
  {
    "title": "Intent ___ містять додаткові дані, передані в Intent.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "arguments",
        "isTrue": false
      },
      {
        "title": "extras",
        "isTrue": true
      },
      {
        "title": "parameters",
        "isTrue": false
      },
      {
        "title": "properties",
        "isTrue": false
      }
    ]
  },
  {
    "title": "StateFlow - це потік даних, який видає поточні та нові оновлення стану.",
    "type": "radioButtons",
    "answers": [
      {
        "title": "Так",
        "isTrue": true
      },
      {
        "title": "Ні",
        "isTrue": false
      }
    ]
  }
]
""".trimIndent()
