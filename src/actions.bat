@echo off
:: 200 = PC herunterfahren
IF %1 EQU 200 (
    timeout /t 5 /nobreak
    shutdown /s
) ELSE (
    :: 300 = PC Ruhezustand / Sleep
    IF %1 EQU 300 (
        timeout /t 5 /nobreak
        rundll32 powrprof.dll, SetSuspendState
    ) ELSE (
    :: 400 = PC sperren
        IF %1 EQU 400 (
            timeout /t 5 /nobreak
            rundll32.exe user32.dll, LockWorkStation
        ) ELSE (
        :: Alle Codes überprüft -> Mache gar nichts
            timeout /t 5 /nobreak
        )
    )
)