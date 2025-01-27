# Evaluación Conjunta del Segundo Parcial 😔

Este proyecto forma parte de la **Evaluación Conjunta del Segundo Parcial** y consiste en la implementación de un sistema basado en microservicios para la **gestión de proovedores y productos**. Incluye funcionalidades clave como:
Asociar un proveedor a un producto.
Listar los productos de un proveedor.
Listar los proveedores de un producto.
Eliminar la relación entre un proveedor y un producto
Además de persistencia en una base de datos MySQL alojada en Docker.

---

## 🔧 **Tecnologías Utilizadas**

- **Java** con **Spring Boot**: Framework para la construcción de microservicios.
- **Hibernate (JPA)**: Manejo de la capa de persistencia.
- **MySQL**: Base de datos relacional.
- **Docker**: Contenedor para la base de datos MySQL.
- **RestTemplate** y **Feign Client**: Comunicación entre microservicios.
- **Maven**: Gestión de dependencias.

---

## ⚙️ **Funcionalidades del Sistema**

### 🟢 Microservicio `micro-proveedores`

- **CRUD de proveedores**: Crear, listar, actualizar y eliminar proveedores.
- **Gestión de relaciones muchos a muchos**:
  - Asignar proveedor a producto.
  - Elimar proveedores a productos.
  - Listar los productos asociados a un proovedor.
  - Listar proovedores que tienen un producto específico.
- **Persistencia en MySQL**: Los datos se almacenan de forma estructurada y pueden ser consultados posteriormente.

### 🔵 Microservicio `micro-productos`

- **Gestión de productos**: CRUD básico para productos.
- **Comunicación entre microservicios**: Los productos se integran con el microservicio `micro-proveedores`.

---

## 📁 **Estructura del Proyecto**

### `micro-proovedores`

- `controller`: Controladores REST para manejar las operaciones de proovedor y asignaciones.
- `service`: Lógica de negocio para la gestión de proovedores.
- `model.entity`: Entidades de la base de datos (`Provedor` y `Producto`).
- `repository`: Interfaces JPA para interactuar con MySQL.
- `clients`: Cliente Feign para la comunicación con `micro-producto`.

### `micro-productos`

- Estructura similar a `micro-provedor`, con funcionalidades específicas para la gestión de productos.

---

## 🚀 **Instrucciones de Ejecución**

### 1. **Configurar MySQL con Docker**

Se ejecuta el siguiente comando para crear un contenedor MySQL:

```bash
docker run -d \
--name mysql-container \
-e MYSQL_ROOT_PASSWORD=abcd \
-e MYSQL_DATABASE=sisdb_examen \
-e MYSQL_USER=root123 \
-e MYSQL_PASSWORD=abcd \
-p 3307:3306 \
mysql:8.0
```

La base de datos la puedes abrir en MySQL Workbench con los parámetros de conexión de arriba, y se verá así:

<div align="center">
<img width="600" alt="bd" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/workbench.png" />
</div>

---

**micro-provedor y producto define su _application.properties_ de esta forma:**

<div align="center">
<img width="600" alt="micro-roles" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/applicaationproperties.png" />
</div>

### 2. **Levantar los Microservicios**

1. Clona el repositorio y navega al directorio raíz.
2. En cada microservicio (`micro-provedor` y `micro-producto`), compila y ejecuta:

```bash
mvn spring-boot:run
```

3. Los microservicios estarán disponibles en:

- `micro-provedor`: http://localhost:8003/api/proveedores
- `micro-producto`: http://localhost:8002/api/productos

### 3. **Pruebas de Funcionalidad**

### PRODUCTOS

- Listar los productos disponibles:
  - **GET:** http://localhost:8002/api/productos

<div align="center">
<img width="600" alt="ver_usuarios" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/VerProveedorProducto.png" />
</div>
<br>

- Crear un producto:
  - **POST:** http://localhost:8002/api/productos
    `json
{
  "nombre": "Pepe",
  "apellido": "Bottles",
  "email": "pepe@chimi.com",
  "fechaNacimiento": "2002-04-16",
  "telefono": "0982343722"
}
`
    <div align="center">
    <img width="600" alt="crear_user" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/crear_producto.png" />
    </div>
    <br>
- Eliminar un producto:
  - **DELETE:** http://localhost:8002/api/productos/{productoId}

<div align="center">
<img width="600" alt="elim_user" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/EliminarProducto.png" />
</div>
<br>

### PROVEDORES

- Listar los provedores disponibles, y los productos que están asignados:
  - **GET:** ttp://localhost:8003/api/proveedores

<div align="center">
<img width="600" alt="ver_rol" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/VerProductosProveedor.png" />
</div>
<br>

- Crear un provedor:
  - **POST:** ttp://localhost:8003/api/proveedores
    ```json
    {
      "nombre": "DB Administrator 6",
      "descripcion": "Admin de las BD hehe"
    }
    ```

<div align="center">
<img width="600" alt="crear_rol" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/crear_proveedor.png" />
</div>
<br>

- Eliminar un provedor:
  - **DELETE:** ttp://localhost:8003/api/proveedores/{provedorId}

<div align="center">
<img width="600" alt="elim_rol" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/EliminarProveedor.png" />
</div>
<br>

- Asignar un provedor a un producto:
  - **POST:** http://localhost:8003/api/proveedores/{provedorId}/productos/{productoId}
    ```json
    {
      "id": 3
    }
    ```

<div align="center">
<img width="600" alt="asign_rol" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/AsociarProveedorProducto.png" />
</div>
<br>

- Revocar provedor a un producto:
  - **DELETE:** http://localhost:8003/api/proveedores/{provedorId}/productos/{productoId}

<div align="center">
<img width="600" alt="revocar_rol" src="https://github.com/PaolaMoncayo/Moncayo_Examen2_Distribuidas/blob/main/img/EliminarRelacion.pnge" />
</div>
<br>

---

## 💻 Tecnologías Utilizadas

- Spring Boot: Para la creación de los microservicios.
- Spring Security: Para la gestión de autenticación y autorización.
- Spring Data JPA: Para la gestión de acceso a la base de datos.
- MySQL: Base de datos relacional.

## Licencia

Este proyecto está bajo la licencia MIT.

---
