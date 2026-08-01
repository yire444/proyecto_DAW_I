-- INSERT DE TIPOS DE DOCUMENTO
INSERT INTO DocumentTypes (name) VALUES
                                     ('DNI'),
                                     ('Carnet de Extranjería'),
                                     ('Pasaporte');

-- INSERT DE PUESTOS DE TRABAJO
INSERT INTO JobPosition (name, description) VALUES
                                                ('Desarrollador Frontend Junior', 'Construcción y maquetación de interfaces.'),
                                                ('Desarrollador Backend Junior', 'Diseño, desarrollo y mantenimiento de APIs RESTful.'),
                                                ('Desarrollador Full-Stack', 'Desarrollo integral de aplicaciones web.'),
                                                ('Ingeniero de Calidad de Software (QA)', 'Diseño y ejecución de pruebas de software.'),
                                                ('DevOps Engineer', 'Automatización de procesos de despliegue continuo (CI/CD).');

-- INSERT DE DEPARTAMENTOS DE TRABAJO
INSERT INTO Departament (name) VALUES
                                   ('Tecnologías de la Información (TI)'),
                                   ('Desarrollo de Software'),
                                   ('Control de Calidad (QA)'),
                                   ('Recursos Humanos (HR)');

-- INSERT DE TIPOS DE CONTRATO
INSERT INTO ContractTypes (name) VALUES
                                     ('Plazo Fijo'),
                                     ('Indeterminado'),
                                     ('Prácticas / Formativo'),
                                     ('Locación de Servicios');

-- INSERT DE HORARIOS / TURNOS DE TRABAJO
INSERT INTO WorkShifts (name, startTime, endTime) VALUES
                                                      ('Turno Mañana', '08:00:00', '17:00:00'),
                                                      ('Turno Tarde', '14:00:00', '22:00:00');

-- INSERT DE SEGUROS MEDICOS
INSERT INTO InsuranceScheme (name) VALUES
                                       ('EsSalud'),
                                       ('EsSalud + Beneficio Clínica Particular (EPS)');

-- INSERT DE RÉGIMEN PENSIONISTA
INSERT INTO PensionScheme (name) VALUES
                                     ('ONP (Sistema Nacional de Pensiones)'),
                                     ('AFP (Sistema Privado de Pensiones)');

-- INSERT DE BANCOS
INSERT INTO Banks (name) VALUES
                             ('BCP'),
                             ('Interbank');