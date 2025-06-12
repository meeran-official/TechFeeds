# PowerShell script to find and show APK locations
Write-Host "Looking for APK files in TechFeed project..." -ForegroundColor Green

$debugApk = ".\app\build\outputs\apk\debug\app-debug.apk"
$releaseApk = ".\app\build\outputs\apk\release\app-release.apk"

if (Test-Path $debugApk) {
    Write-Host "✅ Debug APK found:" -ForegroundColor Green
    Write-Host "   $((Get-Item $debugApk).FullName)" -ForegroundColor Cyan
    $debugSize = [math]::Round((Get-Item $debugApk).Length / 1MB, 2)
    Write-Host "   Size: $debugSize MB" -ForegroundColor Yellow
} else {
    Write-Host "❌ Debug APK not found at: $debugApk" -ForegroundColor Red
}

if (Test-Path $releaseApk) {
    Write-Host "✅ Release APK found:" -ForegroundColor Green
    Write-Host "   $((Get-Item $releaseApk).FullName)" -ForegroundColor Cyan
    $releaseSize = [math]::Round((Get-Item $releaseApk).Length / 1MB, 2)
    Write-Host "   Size: $releaseSize MB" -ForegroundColor Yellow
} else {
    Write-Host "❌ Release APK not found at: $releaseApk" -ForegroundColor Red
}

Write-Host "`nTo install on device:" -ForegroundColor Green
Write-Host "  adb install app-debug.apk" -ForegroundColor Cyan
