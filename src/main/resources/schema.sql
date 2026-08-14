-- ==========================================
-- 1. TABLAS SECUNDARIAS (CATÁLOGOS)
-- ==========================================

-- TIPO DE DOCUMENTO
CREATE TABLE tbl_document_type (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(50) NOT NULL UNIQUE
);

--TIPO DE PLAN
CREATE TABLE tbl_billing_cycle(
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL UNIQUE,
                                    months INT NOT NULL ,
                                    discount DECIMAL(5,2) DEFAULT 0.00

);

--TIPO DE FACTURACIÓN
CREATE TABLE tbl_plan_type (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(50) NOT NULL UNIQUE,
                               price DOUBLE NOT NULL
);

--EMPRESA
CREATE TABLE TBL_COMPANY (
                             id INT AUTO_INCREMENT PRIMARY KEY,
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
                                   id INT IDENTITY PRIMARY KEY,
                                   name VARCHAR(30) NOT NULL UNIQUE
);

-- HORARIOS / TURNOS DE TRABAJO
CREATE TABLE tbl_work_shift (
                                 id INT IDENTITY PRIMARY KEY,
                                 name VARCHAR(50) NOT NULL UNIQUE,
                                 startTime TIME NOT NULL,
                                 endTime TIME NOT NULL
);

-- SEGUROS MEDICOS
CREATE TABLE tbl_insurance_scheme (
                                       id INT IDENTITY PRIMARY KEY,
                                       name VARCHAR(50) NOT NULL UNIQUE
);

-- REGIMÉN PENSIONISTA
CREATE TABLE tbl_pension_scheme (
                                     id INT IDENTITY PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL UNIQUE
);

-- BANCOS
CREATE TABLE tbl_bank (
                           id INT IDENTITY PRIMARY KEY,
                           name VARCHAR(50) NOT NULL UNIQUE
);


-- ==========================================
-- 2. TABLAS PRINCIPALES (EMPLEADOS)
-- ==========================================

-- TABLE EMPLOYEE
CREATE TABLE tbl_employees (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(50) NOT NULL,
                               lastname VARCHAR(50) NOT NULL,
                               birthdate DATE NOT NULL,
                               gender CHAR(1) NOT NULL,
                               address VARCHAR(150),
                               MOBILE_PHONE VARCHAR(15) NOT NULL UNIQUE,
                               PERSONAL_EMAIL VARCHAR(50) NOT NULL UNIQUE,
                               CORPORATE_EMAIL VARCHAR(50) UNIQUE,
                               documentTypeId INT NOT NULL,
                               DOCUMENT_NUMBER VARCHAR(12) NOT NULL UNIQUE,
                               jobPositionId INT NOT NULL,
                               departamentId INT NOT NULL,
                               START_DATE DATE NOT NULL,
                               salary DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                               contractTypeId INT NOT NULL,
                               workShiftId INT NOT NULL,
                               insuranceSchemeId INT NOT NULL,
                               pensionSchemeId INT NOT NULL,
                               status BOOLEAN NOT NULL DEFAULT TRUE,
                               CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT FK_Employee_DocumentType FOREIGN KEY (documentTypeId) REFERENCES tbl_document_type(id),
                               CONSTRAINT FK_Employee_JobPosition FOREIGN KEY (jobPositionId) REFERENCES tbl_job_positions(id),
                               CONSTRAINT FK_Employee_Departament FOREIGN KEY (departamentId) REFERENCES tbl_departament(id),
                               CONSTRAINT FK_Employee_ContractType FOREIGN KEY (contractTypeId) REFERENCES tbl_contract_types(id),
                               CONSTRAINT FK_Employee_WorkShift FOREIGN KEY (workShiftId) REFERENCES tbl_work_shifts(id),
                               CONSTRAINT FK_Employee_InsuranceScheme FOREIGN KEY (insuranceSchemeId) REFERENCES tbl_insurance_schemes(id),
                               CONSTRAINT FK_Employee_PensionScheme FOREIGN KEY (pensionSchemeId) REFERENCES tbl_pension_schemes(id),
                               CONSTRAINT CHK_Employee_Gender CHECK (gender IN ('F', 'M'))
);

-- CUENTAS DE EMPLEADOS (Bancarias)
CREATE TABLE tbl_employee_bank_accounts (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            employeeId INT NOT NULL,
                                            bankId INT NOT NULL,
                                            accountNumber VARCHAR(20) NOT NULL,
                                            cciNumber CHAR(20) NOT NULL,
                                            accountType VARCHAR(20) NOT NULL,
                                            isSalaryAccount BOOLEAN NOT NULL DEFAULT TRUE,
                                            status BOOLEAN NOT NULL DEFAULT TRUE,
                                            createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                            CONSTRAINT FK_BankAccount_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id) ON DELETE CASCADE,
                                            CONSTRAINT FK_BankAccount_Bank FOREIGN KEY (bankId) REFERENCES tbl_banks(id),
                                            CONSTRAINT CHK_BankAccount_Type CHECK (accountType IN ('Ahorros', 'Corriente'))
);

-- EMERGENCY CONTACT TABLE
CREATE TABLE tbl_emergency_contact (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(50) NOT NULL,
                                       employeeId INT NOT NULL,
                                       relationShip VARCHAR(50) NOT NULL,
                                       mobilePhone VARCHAR(15) NOT NULL,
                                       address VARCHAR(250) NOT NULL,
                                       CONSTRAINT FK_EmergencyContact_Employee FOREIGN KEY (employeeId) REFERENCES tbl_employees(id) ON DELETE CASCADE
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