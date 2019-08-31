package ru.byprogminer.modbot

import ru.byprogminer.modbot.storage.GlobalStorage

class DefaultEventBus(override val storage: GlobalStorage, scheduleThreads: Int = 1): AbstractEventBus(scheduleThreads)
