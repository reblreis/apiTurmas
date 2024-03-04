create table aluno(
	id_aluno		uuid			primary key,
	nome			varchar(150)	not null,
	matricula		varchar(10)		not null,
	cpf				varchar(15)		not null
);

create table professor(
	id_professor	uuid			primary key,
	nome			varchar(150)	not null,
	telefone		varchar(15)		not null,
	matricula_id	uuid	
);

create table turma(
	id_turma		uuid			primary key,
	nome			varchar(50)		not null,
	dataInicio		date			not null,
	dataTermino		date,
	professor_id	uuid,
	foreign key(professor_id) 
		references professor(id),
	matricula_id	uuid
);

create table matricula(
	id_matricula	uuid		primary key,
	turma_id		uuid,
	aluno_id		uuid,
	dataMatricula	date		not null,
	foreign key(turma_id) 
		references turma(id),
	foreign key(aluno_id) 
		references aluno(id)
);