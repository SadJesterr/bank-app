# bank-app

Это приложение создано для обеспечения безопасности банковского приложения. 

Инструкция к применению:

1. Установка зависимостей
Перед началом убедитесь, что установлены:

Docker (версия 20.10+)

Docker Compose (версия 1.29+)

Java 17 (для standalone-режима)

Kubernetes CLI (если развертываете в кластере)

Запуск через Docker (рекомендуемый способ)
Склонируйте репозиторий:
git clone https://github.com/SadJesterr/bank-app.git
cd bank-app

Соберите и запустите контейнеры:
docker-compose up --build

Приложение будет доступно на http://localhost:8080

Логи можно просмотреть: docker-compose logs -f
