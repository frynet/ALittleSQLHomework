package static.glossary

object Roles : Iterable<String>, Collection<String> {

    private val items = listOf(
        "бабочка",
        "собака",
        "тигр",
        "лошадь",
        "птица",
        "утка",
        "мама",
        "папа",
        "сестра",
        "брат",
        "сотрудник",
        "учитель",
        "воспитатель",
        "помощник",
        "доктор",
        "пилот",
        "солдат",
        "девочка",
        "мальчик",
        "юноша",
        "боксёр",
        "гимнастка",
        "муравей",
        "паук",
        "тритон",
        "лягушка",
        "змея",
        "акула",
        "дельфин",
        "менеджер",
        "водитель",
        "пассажир",
        "рассказчик",
        "рабочий",
        "грузчик",
        "няня",
        "домохозяйка",
        "корова",
        "жеребёнок",
        "кенгурёнок",
        "ягнёнок",
        "сокол",
        "голубь",
        "обидчик",
        "жертва",
        "победитель",
        "детектив",
        "случайная жертва",
        "убийца",
        "дворецкий",
        "управляющий",
        "канцлер",
        "садовник",
        "дочь",
        "мачеха"
    )

    override fun iterator() = items.iterator()

    override val size = items.size

    override fun contains(element: String) = items.contains(element)

    override fun containsAll(elements: Collection<String>) = items.containsAll(elements)

    override fun isEmpty() = items.isEmpty()
}