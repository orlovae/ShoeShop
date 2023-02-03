package ru.aleksandrorlov.shoeshop.config

import ru.aleksandrorlov.shoeshop.model.Shoe

object FakeData {
    private val fakeList = mutableListOf<Shoe>(
        Shoe(0,"Кеды", "Reebok", 43, "Яркие кеды из титана, выгодно подчеркнут ваш стиль"),
        Shoe(1,"Валенки", "Adidas", 28, "Теплые пушистые валенки из натуральной шерсти бурундуков"),
        Shoe(2,"Сапоги", "Котофей", 16, "Блестящие чёрные сапоги на высоком каблуке остват незабываемый след в вашей спальне"),
        Shoe(3,"Сандали", "Скороход", 49, "Легкие и воздушные сандали, помогут пережить лето"),
        Shoe(4,"Туфли", "No Name", 67, "Красные туфли из крокодила, сделают вас звездой любой вечеринки"),
    )
    fun getAll(): List<Shoe> {
        return fakeList
    }

    fun getId(id: Int): Shoe {
        return fakeList[id]
    }

    fun addShoe(shoe: Shoe): List<Shoe> {
        fakeList.add(shoe)
        return fakeList
    }
}