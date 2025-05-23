import ru.nsu.khubanov.dsl.ConfigBuilder

// 1. Создаём билдер
def builder = new ConfigBuilder()

// 2. Определяем задачи (tasks)
builder.tasks {
    task('1.1') {
        name = 'Пирамидальная сортировка'
        maxScore = 1
        softDeadline = '2025-02-14'
        hardDeadline = '2025-02-21'
    }
    task('2.1') {
        name = 'Пиццерия'
        maxScore = 1
        softDeadline = '2025-03-07'
        hardDeadline = '2025-03-14'
    }
    task('1.3') {
        name = 'Змейка'
        maxScore = 1
        softDeadline = '2025-02-28'
        hardDeadline = '2025-04-04'
    }
    task('4.1') {
        name = 'Авт. проверка задач'
        maxScore = 1
        softDeadline = '2025-04-18'
        hardDeadline = '2025-05-02'
    }
    task('1.2') {
        name = 'Простые числа'
        maxScore = 1
        softDeadline = '2025-05-16'
        hardDeadline = '2025-05-30'
    }
}

// 3. Определяем группы и студентов (groups)
builder.groups {
    group('OOP-2025') {
        student('barsheva') {
            fullName = 'Барышева Олеся'
            repo     = 'https://github.com/lbarysheva/OOP'
        }
        student('bun') {
            fullName = 'Бунь Павел'
            repo     = 'https://github.com/PavelBun/OOP'
        }
        student('gavriliev') {
            fullName = 'Гаврильев Максим'
            repo     = 'https://github.com/Cordip/OOP'
        }
        student('kupalov') {
            fullName = 'Купалов Артём'
            repo     = 'https://github.com/MCBivis/OOP'
        }
        student('tomilin') {
            fullName = 'Томилин Тимофей'
            repo     = 'https://github.com/nAr1c0/OOP'
        }
        student('faranosov') {
            fullName = 'Фараносов Дмитрий'
            repo     = 'https://github.com/system1cls/OOP'
        }
        student('charitonov') {
            fullName = 'Харитонов Алексей'
            repo     = 'https://github.com/AlexCharitonov/OOP'
        }
        student('khubanov') {
            fullName = 'Хубанов Очир'
            repo     = 'https://github.com/ryasuke1/OOP'
        }
    }
}

// 4. Назначения (assignments)
builder.assignments {
    ['badyrtenov','barsheva','bun','gavriliev','kupalov','slabunova',
     'sui','tomilin','rahmanov','charitonov','khubanov'].each { nick ->
        // Каждому студенту назначаются все 5 задач
        ['1.1','2.1','1.3','4.1','1.2'].each { taskId ->
            assign(taskId, nick)
        }
    }
}

// 5. Контрольные точки (checkpoints)
builder.checkpoints {
    checkpoint('Quiz1') { date = '2025-02-21' }
    checkpoint('Quiz2') { date = '2025-03-14' }
    checkpoint('Midterm') { date = '2025-04-04' }
    checkpoint('Quiz3') { date = '2025-04-25' }
    checkpoint('Final')   { date = '2025-05-30' }
}

// 6. Настройки (settings)
builder.settings {
    semesterStart = '2025-02-01'   // начало семестра
    activityBonus = 0.2            // +0.2 балла за каждую активную неделю
    gradeMap = [5:'Отлично',4:'Хорошо',3:'Удовл',2:'Неуд']
}


// 7. Возвращаем готовый Config
return builder.build()

