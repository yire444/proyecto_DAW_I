-- ==========================================
-- 1. INSERT DE CATÁLOGOS SECUNDARIOS
-- ==========================================

-- INSERT DE TIPOS DE DOCUMENTO
INSERT INTO tbl_document_type (name) VALUES
                                         ('DNI'),
                                         ('Carnet de Extranjería'),
                                         ('Pasaporte');

-- INSERT DE TIPOS DE CICLOS
INSERT INTO tbl_billing_cycle (name, months, discount) VALUES
                                                           ('Mensual', 1, 0.00),
                                                           ('Anual', 12, 20.00);

-- INSERT DE TIPOS DE PLANES
INSERT INTO tbl_plan_type (name, price) VALUES
                                            ('Starter', 29.00),
                                            ('Business', 79.00),
                                            ('Enterprise', 199.00);


-- INSERT DE TIPOS DE CONTRATO
INSERT INTO tbl_contract_type (name) VALUES
                                         ('Plazo Fijo'),
                                         ('Indeterminado'),
                                         ('Prácticas / Formativo'),
                                         ('Locación de Servicios');


-- INSERT DE SEGUROS MEDICOS
INSERT INTO tbl_insurance_scheme (name) VALUES
                                            ('EsSalud'),
                                            ('EsSalud + Beneficio Clínica Particular (EPS)');

-- INSERT DE RÉGIMEN PENSIONISTA
INSERT INTO tbl_pension_scheme (name) VALUES
                                          ('ONP (Sistema Nacional de Pensiones)'),
                                          ('AFP (Sistema Privado de Pensiones)');

-- INSERT DE BANCOS
INSERT INTO tbl_bank (name) VALUES
                                ('BCP'),
                                ('Interbank');