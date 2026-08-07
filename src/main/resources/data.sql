-- ==========================================
-- 1. INSERT DE CATÁLOGOS SECUNDARIOS
-- ==========================================

-- INSERT DE TIPOS DE DOCUMENTO
INSERT INTO tbl_document_type (name) VALUES
                                          ('DNI'),
                                          ('Carnet de Extranjería'),
                                          ('Pasaporte');

INSERT INTO tbl_plan_type (name, price) VALUES
                                            ('Starter', 29.00),
                                            ('Business', 79.00),
                                            ('Enterprise', 199.00);

-- INSERT DE PUESTOS DE TRABAJO
INSERT INTO tbl_job_positions (name, description) VALUES
                                                      ('Desarrollador Frontend Junior', 'Construcción y maquetación de interfaces.'),
                                                      ('Desarrollador Backend Junior', 'Diseño, desarrollo y mantenimiento de APIs RESTful.'),
                                                      ('Desarrollador Full-Stack', 'Desarrollo integral de aplicaciones web.'),
                                                      ('Ingeniero de Calidad de Software (QA)', 'Diseño y ejecución de pruebas de software.'),
                                                      ('DevOps Engineer', 'Automatización de procesos de despliegue continuo (CI/CD).');

-- INSERT DE DEPARTAMENTOS DE TRABAJO
INSERT INTO tbl_departament (name, description) VALUES
                                                    ('Tecnologías de la Información (TI)', 'Gestión de infraestructura y sistemas'),
                                                    ('Desarrollo de Software', 'Creación y mantenimiento de aplicaciones'),
                                                    ('Control de Calidad (QA)', 'Pruebas y aseguramiento de calidad'),
                                                    ('Recursos Humanos (HR)', 'Gestión de talento humano');

-- INSERT DE TIPOS DE CONTRATO
INSERT INTO tbl_contract_types (name) VALUES
                                          ('Plazo Fijo'),
                                          ('Indeterminado'),
                                          ('Prácticas / Formativo'),
                                          ('Locación de Servicios');

-- INSERT DE HORARIOS / TURNOS DE TRABAJO
INSERT INTO tbl_work_shifts (name, startTime, endTime) VALUES
                                                           ('Turno Mañana', '08:00:00', '17:00:00'),
                                                           ('Turno Tarde', '14:00:00', '22:00:00');

-- INSERT DE SEGUROS MEDICOS
INSERT INTO tbl_insurance_schemes (name) VALUES
                                             ('EsSalud'),
                                             ('EsSalud + Beneficio Clínica Particular (EPS)');

-- INSERT DE RÉGIMEN PENSIONISTA
INSERT INTO tbl_pension_schemes (name) VALUES
                                           ('ONP (Sistema Nacional de Pensiones)'),
                                           ('AFP (Sistema Privado de Pensiones)');

-- INSERT DE BANCOS
INSERT INTO tbl_banks (name) VALUES
                                 ('BCP'),
                                 ('Interbank');