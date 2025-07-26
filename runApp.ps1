Write-Host "Собираем проект..."

mvn clean package

if (-Not (Test-Path "target/Servlets-1.0.war")) {
    Write-Error "WAR-файл не найден. Сборка не удалась."
    exit 1
}

Write-Host "Запускаем Docker Compose..."
docker-compose up
