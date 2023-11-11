INSERT INTO pizzaria.tb_pizza (
    id, categoria, descricao, nome, urlimagem)
    VALUES
    ('bd861840-6ddd-11ee-b962-0242ac120002', 'suino', 'Linguaça de calabresa de 22 cm', 'Calabresa', 'https%3A%2F%2Fwww.clonepizza.com.br%2Fwp-content%2Fuploads%2Fcalabresa-1.jpg&f=1&nofb=1&ipt=0670aff644d44832199ed4a6601931c8c72c31f0fc47cda94bc747224a1138da&ipo=images'),
    ('bd861de0-6ddd-11ee-b962-0242ac120002', 'queijo', 'Aquela pizza de queijo sem graça', 'Mussarella', 'https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.vfbUspDzN0bNUtyLci4P-QFZC0%26pid%3DApi&f=1&ipt=14dfb3abf475e42f7927433bad817c3e3bf5e2a02ab5011f64dfad6b836ff29b&ipo=images'),
    ('bd861f16-6ddd-11ee-b962-0242ac120002', 'Doce', 'linguiça sem graça', 'Calabresa com Mussarella', 'https%3A%2F%2Fcdn0.tudoreceitas.com%2Fpt%2Fposts%2F9%2F8%2F3%2Fpizza_calabresa_e_mussarela_4389_600.jpg&f=1&nofb=1&ipt=93f12007dd547ffc51f503f58613ba6a2fc26f67fe50c369a905fda7bf0f1631&ipo=images');

INSERT INTO pizzaria.tb_pizzaria (
    id, avaliacao, cep, cidade, endereco, nome, site, telefone) 
    VALUES
    (93094ab9-f10e-459b-b461-b8ce80ea92c0, 5.0, '09973049', 'São Paulo', 'Rua Joaquim de Oliveira Paulino', 'Daniel e Aline Pizzaria', 'www.danlinepizzaria.com.br', '11986262167'),
    (2d8f2ea6-29b4-4e0c-b2fe-486c3ca7ea02, 3.0, '13971237', 'Itapira', 'Rua Pedro Barbanti 726', 'Joremaya pizzaria ME', 'www.joremaya.com.br', '19982229425'),
    (94f21f46-16f1-4672-9302-5a64dd9a85e6, 1.0, '07173050', 'Guarulhos', 'Rua Valença 443', 'Oswalas pizzaria Ltda', 'www.oswalaspizzaria.com.br', '11983421981');

INSERT INTO pizzaria.tb_pizza_pizzaria 
(id, preco, pizza_id, pizzaria_id) 
VALUES
('377fb6ad-a3e8-4b40-844f-b37b01399ec8', 50.02, 'bd861840-6ddd-11ee-b962-0242ac120002', '93094ab9-f10e-459b-b461-b8ce80ea92c0'),
('d86c9a96-242f-4233-97b6-2ab781f31772', 49.03, 'bd861de0-6ddd-11ee-b962-0242ac120002', '93094ab9-f10e-459b-b461-b8ce80ea92c0'),
('ab74138f-419a-4835-8025-0ca32317e6e6', 48.99, 'bd861f16-6ddd-11ee-b962-0242ac120002', '2d8f2ea6-29b4-4e0c-b2fe-486c3ca7ea02'),
('734d92e5-bf4b-4c1d-9f3d-fba387a51e1f', 27.26, 'bd861840-6ddd-11ee-b962-0242ac120002', '2d8f2ea6-29b4-4e0c-b2fe-486c3ca7ea02'),
('378bb293-266d-4dfc-aec8-7ac07b7fd2e1', 56.77, 'bd861f16-6ddd-11ee-b962-0242ac120002', '94f21f46-16f1-4672-9302-5a64dd9a85e6'),
('8682fa06-da7a-4c91-899c-f6598707f3a8', 36.55, 'bd861de0-6ddd-11ee-b962-0242ac120002', '94f21f46-16f1-4672-9302-5a64dd9a85e6');
