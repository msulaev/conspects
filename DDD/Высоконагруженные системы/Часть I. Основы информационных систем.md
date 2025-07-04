![[Pasted image 20250519224630.png]]

#### Надежность
От программного обеспечения обычно ожидается следующее:

 - приложение выполняет ожидаемую пользователем функцию;
 - оно способно выдержать ошибочные действия пользователя или применение
программного обеспечения неожиданным образом;
-  его производительность достаточно высока для текущего сценария использования, при предполагаемой нагрузке и объеме данных;
- система предотвращает любой несанкционированный доступ и неправильнуюэксплуатацию.


Снизить вероятность сбоев до нуля невозможно, следовательно, обычно лучше проектировать механизмы устойчивости к сбоям, которые бы предотвращали переход сбоев в отказы.

Многие критические ошибки фактически происходят из-за недостаточной обработки ошибок; Отсюда и пошел Хаос инжениринг.

###### Аппаратные сбои

Куча проблем с железками и их устранение. Всегда что-то ломается, любой компонент может вылететь. Поэтому происходит сдвиг в сторону систем, способных перенести потерю целых машин, благодаря применению методов устойчивости к сбоям вместо избыточности аппаратного обеспечения или дополнительно к ней. У подобных систем есть и эксплуатационные преимущества: система с одним сервером требует планового
простоя при необходимости перезагрузки машины (например, для установки исправлений безопасности), в то время как устойчивая к аппаратным сбоям система
допускает установку исправлений по узлу за раз, без вынужденного бездействия всей системы. 

###### Программные ошибки 
Примеры:
 - программная ошибка: приводящая к фатальному сбою экземпляра сервера приложения при конкретных плохих входных данных
 - Выходит из под контроля процесс полностью исчерпавший какой-либо общий ресурс
 - Сервис от которого зависит раьота системы замедляется и перестает отвечать на запросы
 - Каскадные сбои, при которых крошечный сбой в одном компонентне вызывает сбой в другом компонентне а тот в последующийх
 Быстрого решения проблемы систематических ошибок в программном обеспечении не существует.

###### Человеческий фактор
Что делаеть?
- правильное проектирование систем и абстракиця
- расцепление наиболее ломающихся сервисов
- внедрение песочницы
- полное тестирование
- быстрое восстановление
- мониторинг


#### Масшатбируемость

![[Pasted image 20250519230356.png]]![[Pasted image 20250519230415.png]]
Описание нагрузки

Описание производительности
- Как изменится производительность системы, если увеличить параметр нагруз-ки при неизменных ресурсах системы (CPU, оперативная память, пропускная способность сети и т. д.)?
- Насколько нужно увеличить ресурсы при увеличении параметра нагрузки,чтобы производительность системы не изменилась?
А что измерять? Количество записей, время ответа? - зависит от системы

```
Термины «время ожидания» (latency) и «время отклика» (response time)
часто используются как синонимы, хотя это не одно и то же. Время откли-
ка — то, что видит клиент: помимо фактического времени обработки запроса
(время обслуживания, service time), оно включает задержки при передаче
информации по сети и задержки сообщений в очереди. Время ожидания —
длительность ожидания запросом обработки, то есть время, на протяжении
которого он ожидает обслуживания
```

необходимо рассматривать время отклика не как одно число, а как распределение значений, характеристики которого можно определить.

![[Pasted image 20250519230858.png]]
```
Процентили часто используются в требованиях к уровню предоставле-
ния сервиса (service level objectives, SLO) и соглашениях об уровне предоставления сервиса (service level agreements, SLA) — контрактах, описывающих ожидаемыепроизводительность и доступность сервиса. В SLA, например, может быть указано:сервис рассматривается как функционирующий нормально, если его медианное время отклика менее 200 мс, а 99-й процентиль меньше 1 с (когда время отклика больше, это равносильно неработающему сервису), причем в требованиях может быть указано, что сервис должен работать нормально не менее 99,9 % времени.
Благодаря этим метрикам клиентские приложения знают, чего ожидать от сервиса,
```

![[Pasted image 20250519231342.png]]

Хорошая масштабируемая для конкретного приложения архитектура базируется на допущениях о том, какие операции будут выполняться часто, а какие — редко,
то есть на параметрах нагрузки. Если эти допущения окажутся неверными, то
работа архитекторов по масштабированию окажется в лучшем случае напрасной,
а в худшем — приведет к обратным результатам. На ранних стадиях обычно важнее
быстрая работа существующих возможностей в опытном образце системы или не-
проверенном программном продукте, чем его масштабируемость под гипотетиче-
скую будущую нагрузку.


#### Удобство сопровождения

удобство эксплуатации:
- мониторинг
- выяснение причин проблем 
- поддержка актальности программного обеспечения и платформ
- отслеживание влияние различных систем друг на друга
- алерты
- инструменты развертывания и управление конфигурацией
- работы по сопровождению - перенос с одной платформы на другу
- поддержка безопаснотси при изменении конфигурации

##### регулируем сложность

Чем сложнее система, тем больше шанс возникновения ошибок.

Побочная сложность - если сложность возникает в ходе конкретной реализации , а не является неотъемлемой частью

Лучший способ больбы со сложностью - абстракция