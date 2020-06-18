drop database locadora;
create database  locadora;
use locadora;
create table carro(
carro_id int not null auto_increment unique,
carro_marca varchar(40) not null,
carro_modelo varchar(40) not null,
carro_ano int not null,
carro_placa varchar(8) not null unique,
carro_tipo varchar(20) not null,
carro_kilometragem float not null,
carro_tanque int not null,
carro_disponibilidade boolean not null,
carro_vendido boolean not null,
carro_preco double not null,
primary key (carro_id)
);
create table operacoes(
op_id int not null auto_increment unique,
op_tipo varchar(12)not null,
op_carro int not null,
op_valor double not null,
op_kilometragem float not null,
op_tanque int not null,
op_data date default current_timestamp,
primary key(op_id),
foreign key (op_carro) references carro(carro_id)
);
create table caixa (
caixa_saldo double not null,
primary key(caixa_saldo)
);

insert into carro(carro_marca,carro_modelo,carro_ano,carro_placa,carro_tipo,carro_kilometragem,carro_tanque,carro_disponibilidade,carro_vendido)
values ('NISSAN','GTR-R35',2020,'GODZILLA','PREMIUM',0,1,true,false);
select * from carro where carro_placa = "GODZILLA";
select * from carro;


UPDATE carro SET carro_disponibilidade = true WHERE carro_placa = 'GODZILLA';
SELECT * FROM carro WHERE carro_tipo = 'PREMIUM';
Select * from carro WHERE carro_tipo = 'PREMIUM';
select * from operacoes;
select carro_id from carro where carro_id = (select max(carro_id) from carro);

SELECT * FROM carro WHERE carro_placa = 'GODZILLA' and carro_vendido = false;
select * from operacoes where op_data between '2020-06-17' and '2020-07-18';
Select * from carro WHERE carro_vendido = false and carro_preco between 1 and 1200;
select op_valor from operacoes;
select op_valor from operacoes where sum(op_tipo = 'COMPRA');
select op_valor from operacoes where op_tipo = 'DEVOLUCAO' or 'VENDA';
SELECT SUM(op_valor) FROM operacoes;