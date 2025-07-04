### **Страница 1: Введение и Основополагающее Мышление**

**Цель данного руководства:**  
Это руководство — практическая выжимка из книги Марка Уинтерингема. Его цель — не заменить книгу, а предоставить вам структурированную методичку для немедленного внедрения генеративного ИИ (Large Language Models, LLM) в вашу ежедневную работу по тестированию и обеспечению качества. Мы сфокусируемся на том, **как** это делать, **зачем**, и **каких ошибок** следует избегать.

**Ключевая философия: Инструмент как усилитель, а не замена**  
Главный посыл книги, который должен стать вашей мантрой: **LLM не заменяет тестировщика, а усиливает его.** Представьте себе LLM как чрезвычайно быстрого, эрудированного, но абсолютно лишенного опыта и здравого смысла стажера. Он может выполнить любую задачу, но только если вы дадите ему безупречно четкие инструкции, полный контекст и будете тщательно проверять результат. Ваша ценность — в критическом мышлении, опыте и умении ставить правильные задачи.

**Три столпа успешного использования LLM в тестировании:**

1. **Мышление (Mindset):** Ваше отношение к инструменту и понимание его ограничений. Это фундамент.
    
2. **Техника (Technique):** Ваши навыки промпт-инжиниринга и умение разбивать сложные задачи на простые, понятные для LLM.
    
3. **Контекст (Context):** Ваша способность предоставить LLM всю необходимую информацию о вашем проекте, чтобы ее ответы были релевантными, а не абстрактными.
    

**Модель «Область Влияния»: Как работает синергия человека и ИИ**  
Эта модель наглядно показывает, почему симбиоз, а не замена, является ключом к успеху.

- **Центр (Индивид):** Ваши базовые способности — анализ, решение проблем, интуиция, скептицизм. Здесь вы ограничены временем и когнитивными искажениями.
    
- **Средний круг (Расширение способностей):** Ваши навыки, усиленные традиционными инструментами (IDE, SQL-клиенты, скрипты).
    
- **Внешний круг (Усиление с LLM):** LLM позволяет вам масштабировать ваши идеи и рутинные операции, освобождая время для более глубокого анализа. LLM выполняет генерацию, трансформацию и обобщение информации.
    

**Практический вывод:** Ваша задача — оставаться в центре этой модели, используя LLM для расширения своего влияния, а не для делегирования мышления.

---

### **Страница 2: Ключевые риски при работе с LLM и как их минимизировать**

Непонимание этих рисков — прямой путь к неэффективной работе и созданию ложного чувства безопасности.

**1. Галлюцинации (Hallucinations)**  
LLM уверенно генерирует ложную информацию, которая выглядит правдоподобно.

- **Пример:**
    
    - **Промпт:** Напиши юнит-тест для этого метода, используя несуществующую библиотеку "MagicAsserts".
        
    - **Ответ LLM:** import com.magic.asserts.*; ... — LLM с готовностью "придумает" синтаксис для несуществующей библиотеки.
        
- **Как бороться:**
    
    - **Перепроверка:** Всегда проверяйте сгенерированный код, ссылки, названия и факты.
        
    - **Принцип "меньше доверия":** Не задавайте вопросы, на которые вы не знаете хотя бы примерного ответа.
        
    - **Проверка допущений (в промптах):** Добавляйте в промпт условие: "Если ты не уверен в ответе, сообщи об этом."
        

**2. Происхождение данных (Data Provenance)**  
LLM обучаются на "сырых" данных из интернета, включая плохие практики.

- **Пример:**
    
    - **Промпт:** Напиши функцию для валидации email на JavaScript.
        
    - **Ответ LLM:** Может сгенерировать устаревшее или неполное регулярное выражение, скопированное с форума 2010 года, которое не учитывает современные доменные зоны.
        
- **Как бороться:**
    
    - **Код-ревью:** Относитесь к сгенерированному коду так же, как к коду, написанному младшим разработчиком. Проводите тщательное ревью.
        
    - **Контекст:** Предоставляйте в промпте ссылки на актуальную документацию или стандарты (например, RFC), которым должен следовать код.
        

**3. Конфиденциальность данных (Data Privacy)**  
Публичные LLM (ChatGPT, Gemini) используют ваши данные для своего обучения.

- **Пример:** Вы вставляете в ChatGPT фрагмент проприетарного кода с багом, чтобы найти ошибку. Этот код теперь может стать частью обучающей выборки OpenAI.
    
- **Как бороться:**
    
    - **Золотое правило:** **Никогда не отправляйте конфиденциальную информацию** (код, данные клиентов, внутреннюю документацию) в публичные LLM.
        
    - **Анонимизация:** Если вам нужно проанализировать структуру данных, замените реальные названия полей и значения на абстрактные (field_1, user_data, и т.д.).
        
    - **Локальные/Корпоративные LLM:** Для работы с чувствительными данными используйте локально развернутые модели или корпоративные решения с гарантиями конфиденциальности.
        

---

### **Страница 3: Искусство Промпт-инжиниринга, Принцип №1**

**Принцип 1: Пишите четкие и конкретные инструкции**  
Общение с LLM отличается от общения с человеком. Будьте предельно точны.

**Тактика 1: Используйте разделители (Delimiters)**  
Четко отделяйте разные части вашего промпта: инструкцию, данные, примеры.

- **Плохо:** Summarize the text I pasted below.
    
- **Хорошо:**
    
    Generated code
    
    ```
    Summarize the text delimited by triple backticks into a single sentence.
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    
    [Здесь ваш текст]
    
    Generated code
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

**Тактика 2: Запрашивайте структурированный вывод (Structured Output)**  
Это упрощает автоматическую обработку и повышает предсказуемость.

- **Плохо:** Generate some test users.
    
- **Хорошо:** Generate 5 test users. Provide the output in JSON format. Each user object must include "id", "username", and "role". The role must be either "admin" or "editor".
    

**Тактика 3: Проверяйте выполнение условий (Check for Assumptions)**  
Научите LLM обрабатывать случаи, когда задача невыполнима.

- **Плохо:** Extract the name of the person from the text. (Если имени нет, LLM может его выдумать).
    
- **Хорошо:** Extract the name of the person from the text. If no name is found, return the string "No name found".
    

**Тактика 4: Обучение на нескольких примерах (Few-shot Prompting)**  
Покажите LLM, что именно вы хотите. Это одна из самых мощных техник.

- **Пример для преобразования баг-репорта:**
    
    Generated code
    
    ```
    Translate the user's bug report into a formal bug ticket.
    User report: "Hey, the login button doesn't work, I click it and nothing happens."
    Formal ticket:
    Title: Login button is unresponsive
    Steps: 1. Navigate to login page. 2. Enter valid credentials. 3. Click "Login" button.
    Expected: User is redirected to dashboard.
    Actual: No action occurs.
    
    Now, translate this user report:
    User report: "I can't upload my picture, it says file is too big but it's only 1MB."
    Formal ticket: ???
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

---

### **Страница 4: Искусство Промпт-инжиниринга, Принцип №2**

**Принцип 2: Дайте модели «время подумать»**  
Сложные задачи требуют декомпозиции. Не заставляйте LLM угадывать.

**Тактика 1: Укажите шаги для выполнения (Specify the Steps)**  
Разбейте большую задачу на маленькие подзадачи.

- **Плохо:** Write a test plan for the new feature.
    
- **Хорошо:**
    
    Generated code
    
    ```
    Create a test plan for the new feature based on the requirements below.
    Perform the following steps:
    1. Summarize the feature in one paragraph.
    2. Identify 5 potential risks related to functionality, usability, and performance.
    3. For each risk, suggest one high-level test strategy.
    4. Format the final output as a Markdown document.
    
    Requirements:
    [...вставить требования...]
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

**Тактика 2: Попросите модель сначала выработать собственное решение, а потом дать ответ (Instruct the model to work out its own solution first)**  
Это заставляет LLM провести внутренний анализ, что снижает количество ошибок.

- **Плохо:** Is this code correct? [code snippet]
    
- **Хорошо:**
    
    Generated code
    
    ```
    You are a senior Java developer and a code reviewer.
    Task: Analyze the following student's Java code.
    First, determine if the logic for calculating sales tax is correct. Consider edge cases like zero and negative values.
    Second, identify any violations of standard Java code style.
    Third, provide a corrected version of the code with comments explaining your changes.
    
    Student's code:
    [...вставить код...]
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

---

### **Страница 5: Практическое применение — Генерация тестовых данных**

**От простого к сложному:**

**1. Простая генерация:**

- **Задача:** Нужны данные для заполнения формы.
    
- **Промпт:** Сгенерируй 10 реалистичных имен и фамилий для пользователей из Германии. Выведи в формате "Имя Фамилия", по одному в строке.
    

**2. Генерация структурированных данных:**

- **Задача:** Нужен JSON для API-запроса.
    
- **Промпт:** Создай JSON-объект для продукта. Он должен содержать поля: "productId" (целое число), "productName" (строка), "tags" (массив строк), "dimensions" (вложенный объект с полями "width", "height", "depth" - все числовые).
    

**3. Генерация данных по формальной схеме (OpenAPI/XSD):**

- **Инсайт:** Если у вас есть схема, используйте ее. LLM отлично их понимает.
    
- **Процесс:**
    
    1. Возьмите вашу OpenAPI (Swagger) или XSD схему.
        
    2. **Промпт:**
        
        Generated code
        
        ```
        Ты генератор тестовых данных. Создай 5 валидных JSON-объектов, которые соответствуют следующей OpenAPI-схеме.
        ###
        [...вставить вашу OpenAPI схему...]
        ###
        ```
        
        content_copydownload
        
        Use code [with caution](https://support.google.com/legal/answer/13505487).
        

**4. Трансформация данных:**

- **Задача:** У вас есть данные в CSV, а нужны в SQL.
    
- **Промпт:**
    
    Generated code
    
    ```
    Преобразуй следующие CSV-данные в SQL INSERT-запросы для таблицы 'products'.
    CSV:
    id,name,price
    1,Laptop,999
    2,Mouse,25
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

---

### **Страница 6: Практическое применение — Анализ рисков и планирование тестов**

**Инсайт:** Главная ценность LLM в планировании — не генерация тест-кейсов, а **помощь в идентификации рисков**.

**Процесс работы:**

1. **Начните с себя:** Определите 2-3 очевидных риска для фичи.
    
2. **Привлеките LLM как ассистента:** Попросите ее расширить ваш список.
    
3. **Используйте моделирование для контекста:**
    
    - **Пример (с использованием эвристики SFDIPOT):**
        
        - **Модель:** SFDIPOT (Structure, Function, Data, Interfaces, Platform, Operations, Time).
            
        - **Промпт:**
            
            Generated code
            
            ```
            Ты опытный тестировщик. Проанализируй фичу "Экспорт отчета в PDF".
            Используя эвристику SFDIPOT, предложи по 2 риска для каждой категории (Structure, Function, Data и т.д.).
            
            Описание фичи:
            Пользователь нажимает кнопку "Экспорт в PDF". Система генерирует PDF-файл с данными из текущего отчета и предлагает его для скачивания.
            ```
            
            content_copydownload
            
            Use code [with caution](https://support.google.com/legal/answer/13505487).
            

**Результат:** LLM сгенерирует риски, о которых вы могли не подумать, например:

- **Platform:** "Что произойдет, если у пользователя нет программы для просмотра PDF?"
    
- **Data:** "Как система обработает отчет с 10 000 строк? Не упадет ли она от нехватки памяти?"
    
- **Time:** "Что если пользователь нажмет кнопку экспорта 5 раз за секунду?"
    

---

### **Страница 7: Практическое применение — Помощь в UI и API автоматизации**

**Золотое правило:** Автоматизируйте **подзадачи**, а не весь тест целиком.

**Рецепт 1: Быстрое создание Page Object**

1. Откройте страницу в браузере, скопируйте ее HTML.
    
2. **Промпт:**
    
    Generated code
    
    ```
    Ты эксперт по Selenium. Создай класс Page Object на Java для следующего HTML. Используй аннотации @FindBy. Локаторы должны быть максимально стабильными (предпочитай id или data-testid).
    
    HTML:
    ###
    [...вставить HTML...]
    ###
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

**Рецепт 2: Преобразование cURL в API-тест**

1. В DevTools вашего браузера найдите нужный сетевой запрос.
    
2. Скопируйте его как cURL-команду.
    
3. **Промпт:**
    
    Generated code
    
    ```
    Преобразуй этот cURL-запрос в Java-код с использованием библиотеки Rest-Assured. Метод должен принимать POJO в качестве тела запроса.
    
    cURL:
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    
    [...вставить cURL...]
    
    Generated code
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    
    После этого вы можете попросить LLM сгенерировать и сам POJO-класс.
    

---

### **Страница 8: Практическое применение — Исследовательское тестирование и отчетность**

**1. Генерация идей для исследовательского тестирования (ET):**

- **Процесс:** Используйте LLM как партнера для мозгового штурма.
    
- **Промпт:**
    
    Generated code
    
    ```
    Я провожу сессию исследовательского тестирования для функции поиска на сайте. Мой чартер: "Исследовать поисковую строку, чтобы обнаружить проблемы с валидацией ввода".
    Я уже проверил пустую строку и длинную строку. Предложи еще 5 неочевидных идей для исследования, которые могут сломать эту функцию.
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    
- **Возможный ответ:** "Попробуйте ввести эмодзи, SQL-инъекцию, текст на другом языке (например, арабском), очень длинное слово без пробелов, или вставить 10КБ текста из буфера обмена."
    

**2. Структурирование заметок по ET:**

- **Процесс:** После сессии у вас есть хаотичные заметки.
    
- **Промпт:**
    
    Generated code
    
    ```
    Преобразуй мои заметки из сессии исследовательского тестирования в структурированный отчет. Отчет должен иметь разделы: "Цель сессии", "Краткое саммари", "Найденные баги (с шагами)", "Открытые вопросы".
    
    Мои заметки:
    ###
    - тестил поиск
    - кнопка не работает на IE11
    - при вводе ' " ' падает с 500 ошибкой
    - долго ищет если ввести %
    - неясно, ищет ли по части слова
    ###
    ```
    
    content_copydownload
    
    Use code [with caution](https://support.google.com/legal/answer/13505487).
    

---

### **Страница 9: Продвинутые техники — RAG и Fine-Tuning**

**Концепция: Как дать LLM доступ к вашим знаниям**

- **Проблема:** Окно контекста ограничено. Вы не можете вставить всю документацию в один промпт.
    
- **Решение:** Техники, которые "подключают" внешние знания.
    

**1. Retrieval-Augmented Generation (RAG)**

- **Аналогия:** Чат-бот с доступом к вашей базе знаний.
    
- **Принцип работы:**
    
    1. **Индексация:** Ваши документы (Confluence, Jira, .txt, .md) преобразуются в векторы и сохраняются в векторной базе данных.
        
    2. **Поиск:** Ваш запрос также преобразуется в вектор. Система находит в базе наиболее "близкие" по смыслу векторы документов.
        
    3. **Аугментация:** Найденные фрагменты документов автоматически добавляются в ваш промпт в качестве контекста.
        
    4. **Генерация:** LLM отвечает на ваш вопрос, уже видя релевантную информацию.
        
- **Идеально для:** Систем вопросов и ответов, чат-ботов поддержки, анализа логов на основе внутренней документации.
    

**2. Fine-Tuning (Дообучение)**

- **Аналогия:** Обучение LLM говорить на "вашем языке" и в "вашем стиле".
    
- **Принцип работы:**
    
    1. **Подготовка датасета:** Вы создаете набор из сотен или тысяч примеров в формате "инструкция -> идеальный ответ".
        
    2. **Обучение:** Вы запускаете процесс дообучения, в ходе которого веса нейронной сети немного изменяются, чтобы лучше соответствовать вашим примерам.
        
- **Идеально для:**
    
    - **Стиль и тон:** Научить модель генерировать ответы в формальном/неформальном/юмористическом стиле.
        
    - **Формат:** Научить модель всегда выводить данные в вашем сложном, проприетарном формате.
        
    - **Специфическая лексика:** Научить модель правильно использовать внутренний сленг и терминологию.
        

**Сравнительная таблица: RAG vs. Fine-Tuning**

|   |   |   |
|---|---|---|
|Критерий|RAG|Fine-Tuning|
|**Цель**|Дать доступ к **фактическим знаниям**|Изменить **стиль и поведение**|
|**Данные**|Использует документы для поиска|Использует пары "промпт-ответ" для обучения|
|**Актуальность**|Данные всегда актуальны (можно обновить базу)|Знания "запекаются" в модель на момент обучения|
|**Сложность**|Проще и быстрее для старта|Сложнее, дольше и дороже|
|**Когда выбрать**|Вам нужно, чтобы LLM отвечала на основе вашей документации.|Вам нужно, чтобы LLM всегда следовала определенному формату или стилю.|

---

### **Страница 10: Заключение и Чек-лист для старта**

**Ключевые выводы:**

1. **Начните с мышления:** Поймите, что LLM — это инструмент, а вы — его оператор.
    
2. **Промпт — это всё:** Качество вашей работы с LLM равно качеству ваших промптов.
    
3. **Контекст решает:** Чем больше релевантного контекста, тем лучше результат. Общие промпты дают общие ответы.
    
4. **Декомпозиция — ваш друг:** Разбивайте большие задачи на маленькие. LLM отлично справляется с микро-задачами.
    
5. **Человек + ИИ > Человек или ИИ:** Используйте сильные стороны каждого: ваше критическое мышление и опыт, скорость и масштаб LLM.
    

**Чек-лист: Ваши первые шаги с LLM в тестировании**

- **[ ] Шаг 1: Выберите одну рутинную задачу.** Не беритесь за все сразу. Идеальный кандидат — задача, которая отнимает у вас много времени и не требует глубокого творческого мышления (например, генерация однотипных тестовых данных).
    
- **[ ] Шаг 2: Сформулируйте четкую цель.** Что именно вы хотите получить? (например, "100 JSON-объектов по моей схеме").
    
- **[ ] Шаг 3: Напишите свой первый промпт.** Используйте принципы из этой методички (разделители, структура, примеры).
    
- **[ ] Шаг 4: Проанализируйте результат.** Что получилось хорошо? Что плохо? Как можно улучшить промпт?
    
- **[ ] Шаг 5: Итерируйте.** Улучшите промпт и попробуйте снова. Сделайте 3-5 итераций.
    
- **[ ] Шаг 6: Сохраните успешный промпт.** Начните создавать свою личную или командную библиотеку промптов.
    
- **[ ] Шаг 7: Поделитесь результатом с командой.** Покажите, как вы сэкономили время. Это лучший способ доказать ценность нового подхода.