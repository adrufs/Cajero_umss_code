# Cajero_umss_code
Código que simula el comportamiento de un cajero,descrpcion de las funcionalidades del cajero automático:

1. Clave de acceso o registro:
    - El usuario debe ingresar una clave para acceder al cajero automático.
    - Si la clave es incorrecta, se muestra un mensaje y se detiene la ejecución.
    - En caso de que sea un nuevo usuario tendra la opcion de registrarse y de poder hacer su primer deposito

2. Menú del cajero automático:
    - El programa presenta un menú con opciones como: retirar, consultar saldo, depositar, transferir y cambiar moneda.
    - El usuario puede elegir una opción según sus necesidades.

3. Retiro de fondos:
    - Si el usuario selecciona la opción de retirar, el programa:
        - Verifica si hay suficientes fondos en la cuenta.
        - Solicita al usuario la cantidad a retirar.
        - Resta la cantidad de fondos disponibles.
        - Muestra un mensaje con la cantidad retirada y el saldo restante.

4. Depósitos:
    - El usuario puede seleccionar la opción de depositar.
    - Se solicita la cantidad a depositar.
    - Se suma la cantidad al saldo actual.

5. Transferencias:
    - Si el usuario elige la opción de transferir, se le pide la cantidad y la cuenta de destino.
    - Se verifica si hay suficientes fondos y se realiza la transferencia.

6. Cambio de moneda:
    - Si el usuario selecciona la opción de cambiar moneda, se le permite convertir dólares a bolivianos (u otra moneda).
    - Se utiliza un tipo de cambio predefinido.

Este simulador trata de cubrir todas las funcionalidades de un cajero.
