-- ==========================================
-- 1. TABLAS SECUNDARIAS (CATÁLOGOS)
-- ==========================================

-- TIPO DE DOCUMENTO
CREATE TABLE tbl_document_type (
                                   id IDENTITY PRIMARY KEY,
                                   name VARCHAR(50) NOT NULL UNIQUE
);

--TIPO DE PLAN
CREATE TABLE tbl_billing_cycle(
                                    id IDENTITY PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL UNIQUE,
                                    months INT NOT NULL ,
                                    discount DECIMAL(5,2) DEFAULT 0.00

);

--TIPO DE FACTURACIÓN
CREATE TABLE tbl_plan_type (
                               id IDENTITY PRIMARY KEY,
                               name VARCHAR(50) NOT NULL UNIQUE,
                               price DOUBLE NOT NULL
);

--EMPRESA
CREATE TABLE tbl_company (
                             id IDENTITY PRIMARY KEY,
                             name_company VARCHAR(255) NOT NULL,
                             ruc VARCHAR(11) NOT NULL UNIQUE,
                             name_holder VARCHAR(255) NOT NULL,
                             last_name_holder VARCHAR(255) NOT NULL,
                             document_type_id INT NOT NULL,
                             document_number VARCHAR(50) NOT NULL,
                             email_company VARCHAR(255) NOT NULL UNIQUE,
                             phone_company VARCHAR(50) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             plan_type_id INT NOT NULL,
                             billing_cycle_id INT NOT NULL,
                             status VARCHAR(50) DEFAULT 'PENDING',
                             verification_code VARCHAR(10),
                             creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--TBL PARA EL MÓDULO DE EMPLEADOS

-- PUESTOS DE TRABAJO
-- DEPARTAMENTOS DE TRABAJO
CREATE TABLE tbl_work_area (
                               id IDENTITY PRIMARY KEY,
                               name VARCHAR(50) NOT NULL UNIQUE,
                               description VARCHAR(255) NOT NULL,
                               status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE tbl_job_position (
                                  id IDENTITY PRIMARY KEY,
                                  name VARCHAR(50) NOT NULL UNIQUE,
                                  description VARCHAR(255) NOT NULL,
                                  status BOOLEAN NOT NULL DEFAULT TRUE,
                                  work_area_id BIGINT NOT NULL,
                                  CONSTRAINT fk_position_work_area FOREIGN KEY (work_area_id) REFERENCES tbl_work_area(id)
);

-- TIPOS DE CONTRATO
CREATE TABLE tbl_contract_type (
                                   id IDENTITY PRIMARY KEY,
                                   name VARCHAR(30) NOT NULL UNIQUE
);

-- HORARIOS / TURNOS DE TRABAJO
CREATE TABLE tbl_work_shift (
                                 id IDENTITY PRIMARY KEY,
                                 name VARCHAR(50) NOT NULL UNIQUE,
                                 startTime TIME NOT NULL,
                                 endTime TIME NOT NULL
);

-- SEGUROS MEDICOS
CREATE TABLE tbl_insurance_scheme (
                                       id IDENTITY PRIMARY KEY,
                                       name VARCHAR(50) NOT NULL UNIQUE
);

-- REGIMÉN PENSIONISTA
CREATE TABLE tbl_pension_scheme (
                                     id IDENTITY PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL UNIQUE
);

-- BANCOS
CREATE TABLE tbl_bank (
                           id IDENTITY PRIMARY KEY,
                           name VARCHAR(50) NOT NULL UNIQUE
);


-- ==========================================
-- 2. TABLAS PRINCIPALES (EMPLEADOS)
-- ==========================================

-- TABLE EMPLOYEE
-- ==========================================
-- TABLA PRINCIPAL: EMPLEADOS
-- ==========================================
CREATE TABLE tbl_employees (
                               id IDENTITY PRIMARY KEY,
                               company_id BIGINT NOT NULL,
                               name VARCHAR(50) NOT NULL,
                               lastname VARCHAR(50) NOT NULL,
                               birthdate DATE NOT NULL,
                               gender CHAR(1) NOT NULL,
                               address VARCHAR(150) NOT NULL,
                               mobile_phone VARCHAR(15) NOT NULL UNIQUE,
                               personal_email VARCHAR(150) NOT NULL UNIQUE,
                               corporate_email VARCHAR(50) UNIQUE,
                               document_type_id BIGINT NOT NULL,
                               document_number VARCHAR(12) NOT NULL UNIQUE,
                               job_position_id BIGINT NOT NULL,
                               department_id BIGINT NOT NULL,
                               start_date DATE NOT NULL,
                               salary DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                               contract_type_id BIGINT NOT NULL,
                               work_shift_id BIGINT NOT NULL,
                               insurance_scheme_id BIGINT NOT NULL,
                               pension_scheme_id BIGINT NOT NULL,
                               status BOOLEAN NOT NULL DEFAULT TRUE,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_employee_company FOREIGN KEY (company_id) REFERENCES tbl_company(id),
                               CONSTRAINT fk_employee_document_type FOREIGN KEY (document_type_id) REFERENCES tbl_document_type(id),
                               CONSTRAINT fk_employee_job_position FOREIGN KEY (job_position_id) REFERENCES tbl_job_position(id),
                               CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES tbl_work_area(id),
                               CONSTRAINT fk_employee_contract_type FOREIGN KEY (contract_type_id) REFERENCES tbl_contract_type(id),
                               CONSTRAINT fk_employee_work_shift FOREIGN KEY (work_shift_id) REFERENCES tbl_work_shift(id),
                               CONSTRAINT fk_employee_insurance_scheme FOREIGN KEY (insurance_scheme_id) REFERENCES tbl_insurance_scheme(id),
                               CONSTRAINT fk_employee_pension_scheme FOREIGN KEY (pension_scheme_id) REFERENCES tbl_pension_scheme(id),
                               CONSTRAINT chk_employee_gender CHECK (gender IN ('F', 'M'))
);

-- CUENTAS DE EMPLEADOS (Bancarias)
CREATE TABLE tbl_employee_bank_accounts (
                                            id IDENTITY PRIMARY KEY,
                                            employee_id BIGINT NOT NULL,
                                            bank_id BIGINT NOT NULL,
                                            account_number VARCHAR(20) NOT NULL UNIQUE,
                                            cci_number CHAR(20) NOT NULL UNIQUE,
                                            account_type VARCHAR(20) NOT NULL,
                                            is_salary_account BOOLEAN NOT NULL DEFAULT TRUE,
                                            status BOOLEAN NOT NULL DEFAULT TRUE,
                                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                            CONSTRAINT fk_bank_account_employee FOREIGN KEY (employee_id) REFERENCES tbl_employees(id) ON DELETE CASCADE,
                                            CONSTRAINT fk_bank_account_bank FOREIGN KEY (bank_id) REFERENCES tbl_banks(id),
                                            CONSTRAINT chk_bank_account_type CHECK (account_type IN ('Ahorros', 'Corriente'))
);

-- CONTACTOS DE EMERGENCIA DE LOS EMPLEADOS
CREATE TABLE tbl_emergency_contact (
                                       id IDENTITY PRIMARY KEY,
                                       name VARCHAR(150) NOT NULL,
                                       employee_id BIGINT NOT NULL,
                                       relationship VARCHAR(50) NOT NULL,
                                       mobile_phone VARCHAR(15) NOT NULL,
                                       address VARCHAR(250) NOT NULL,

                                       CONSTRAINT fk_emergency_contact_employee FOREIGN KEY (employee_id) REFERENCES tbl_employees(id) ON DELETE CASCADE
);


-- ==========================================
-- 3. MÓDULOS DE PAGOS Y TAREAS
-- ==========================================

-- MÓDULO DE PAGOS (NÓMINA / PAYROLL)
CREATE TABLE Payroll (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         employeeId INT NOT NULL,
                         paymentDate DATE NOT NULL,
                         periodStartDate DATE NOT NULL,
                         periodEndDate DATE NOT NULL,
                         baseSalary DECIMAL(10,2) NOT NULL,
                         bonuses DECIMAL(10,2) DEFAULT 0.00,
                         deductions DECIMAL(10,2) DEFAULT 0.00,
                         netSalary DECIMAL(10,2) NOT NULL,
                         status VARCHAR(20) DEFAULT 'Pagado',
                         createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT FK_Payroll_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id)
);

-- MÓDULO DE TAREAS Y PROYECTOS
CREATE TABLE Projects (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(255),
                          startDate DATE NOT NULL,
                          endDate DATE,
                          status VARCHAR(20) DEFAULT 'Activo'
);

CREATE TABLE Tasks (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       projectId INT NOT NULL,
                       employeeId INT NOT NULL,
                       title VARCHAR(100) NOT NULL,
                       description VARCHAR(255),
                       dueDate DATE,
                       status VARCHAR(20) DEFAULT 'Pendiente',
                       createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT FK_Tasks_Project FOREIGN KEY (projectId) REFERENCES Projects(id),
                       CONSTRAINT FK_Tasks_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id)
);


-- ==========================================
-- 4. MÓDULO DE LICENCIAS DE SOFTWARE (SaaS / IA)
-- ==========================================

CREATE TABLE SoftwareLicenses (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  softwareName VARCHAR(100) NOT NULL,
                                  provider VARCHAR(50),
                                  licenseType VARCHAR(50) DEFAULT 'Suscripción Mensual',
                                  totalKeys INT NOT NULL,
                                  availableKeys INT NOT NULL,
                                  expirationDate DATE
);

CREATE TABLE LicenseRequests (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 employeeId INT NOT NULL,
                                 licenseId INT NOT NULL,
                                 requestDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 justification VARCHAR(255),
                                 status VARCHAR(20) DEFAULT 'Pendiente',
                                 approvalDate TIMESTAMP NULL,

                                 CONSTRAINT FK_LicenseRequests_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id),
                                 CONSTRAINT FK_LicenseRequests_License FOREIGN KEY (licenseId) REFERENCES SoftwareLicenses(id)
);

CREATE TABLE AssignedLicenses (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  employeeId INT NOT NULL,
                                  licenseId INT NOT NULL,
                                  assignedDate DATE NOT NULL DEFAULT CURRENT_DATE,
                                  revokedDate DATE NULL,
                                  status VARCHAR(20) DEFAULT 'Activa',

                                  CONSTRAINT FK_AssignedLicenses_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id),
                                  CONSTRAINT FK_AssignedLicenses_License FOREIGN KEY (licenseId) REFERENCES SoftwareLicenses(id)
);


-- ==========================================
-- 5. MÓDULO DE AUTENTICACIÓN Y SEGURIDAD
-- ==========================================

CREATE TABLE tbl_user_credentials (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      employeeId INT NOT NULL UNIQUE,
                                      passwordHash VARCHAR(255) NOT NULL,
                                      activationToken VARCHAR(100) NULL,
                                      tokenExpiration TIMESTAMP NULL,
                                      isActivated BOOLEAN NOT NULL DEFAULT FALSE,
                                      systemRole VARCHAR(20) NOT NULL DEFAULT 'USER',
                                      createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                      CONSTRAINT FK_UserCredentials_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id) ON DELETE CASCADE
);