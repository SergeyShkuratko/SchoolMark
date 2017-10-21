-- README
--
-- to restore:
--   psql dbName < sm.sql
-- to dump:
--   pg_dump dbName > sm.sql

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plperl; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plperl WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plperl; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plperl IS 'PL/Perl procedural language';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: hstore; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS hstore WITH SCHEMA public;


--
-- Name: EXTENSION hstore; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION hstore IS 'data type for storing sets of (key, value) pairs';


--
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_stat_statements IS 'track execution statistics of all SQL statements executed';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


SET search_path = public, pg_catalog;

--
-- Name: role; Type: TYPE; Schema: public; Owner: ixabtpit
--

CREATE TYPE role AS ENUM (
    'director',
    'teacher',
    'student',
    'admin'
);


ALTER TYPE role OWNER TO ixabtpit;

--
-- Name: test_status; Type: TYPE; Schema: public; Owner: ixabtpit
--

CREATE TYPE test_status AS ENUM (
    'new',
    'uploaded',
    'appointed',
    'absent',
    'verified',
    'confirmed',
    'reverification'
);


ALTER TYPE test_status OWNER TO ixabtpit;

--
-- Name: work_status; Type: TYPE; Schema: public; Owner: ixabtpit
--

CREATE TYPE work_status AS ENUM (
    'new',
    'new_value',
    'in_progressappointed',
    'on_verification',
    'verified',
    'confirmed',
    'reverification',
    'uploaded'
);


ALTER TYPE work_status OWNER TO ixabtpit;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: city; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE city (
    id integer NOT NULL,
    region_id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE city OWNER TO ixabtpit;

--
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE city_id_seq OWNER TO ixabtpit;

--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE city_id_seq OWNED BY city.id;


--
-- Name: question_verification_criterions; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE question_verification_criterions (
    id integer NOT NULL,
    question_id integer NOT NULL,
    criterion text NOT NULL
);


ALTER TABLE question_verification_criterions OWNER TO ixabtpit;

--
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE question_verification_criteries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE question_verification_criteries_id_seq OWNER TO ixabtpit;

--
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE question_verification_criteries_id_seq OWNED BY question_verification_criterions.id;


--
-- Name: questions; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE questions (
    id integer NOT NULL,
    template_variant_id integer NOT NULL,
    question text NOT NULL,
    answer text NOT NULL
);


ALTER TABLE questions OWNER TO ixabtpit;

--
-- Name: questions_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE questions_id_seq OWNER TO ixabtpit;

--
-- Name: questions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE questions_id_seq OWNED BY questions.id;


--
-- Name: region; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE region (
    id integer NOT NULL,
    num integer,
    name character varying(100) NOT NULL
);


ALTER TABLE region OWNER TO ixabtpit;

--
-- Name: region_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE region_id_seq OWNER TO ixabtpit;

--
-- Name: region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE region_id_seq OWNED BY region.id;


--
-- Name: registration_url; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE registration_url (
    url character varying(13) NOT NULL,
    role role DEFAULT 'admin'::role NOT NULL,
    school_id integer,
    school_class_id integer
);


ALTER TABLE registration_url OWNER TO ixabtpit;

--
-- Name: school_classes; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE school_classes (
    id integer NOT NULL,
    number integer NOT NULL,
    name text NOT NULL,
    school_id integer NOT NULL
);


ALTER TABLE school_classes OWNER TO ixabtpit;

--
-- Name: school_classes_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE school_classes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE school_classes_id_seq OWNER TO ixabtpit;

--
-- Name: school_classes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE school_classes_id_seq OWNED BY school_classes.id;


--
-- Name: school_types; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE school_types (
    id integer NOT NULL,
    type_name text NOT NULL
);


ALTER TABLE school_types OWNER TO ixabtpit;

--
-- Name: school_types_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE school_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE school_types_id_seq OWNER TO ixabtpit;

--
-- Name: school_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE school_types_id_seq OWNED BY school_types.id;


--
-- Name: schools; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE schools (
    id integer NOT NULL,
    name text NOT NULL,
    region text NOT NULL,
    city text NOT NULL,
    school_type_id integer NOT NULL,
    city_id integer
);


ALTER TABLE schools OWNER TO ixabtpit;

--
-- Name: schools_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE schools_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE schools_id_seq OWNER TO ixabtpit;

--
-- Name: schools_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE schools_id_seq OWNED BY schools.id;


--
-- Name: students; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE students (
    id integer NOT NULL,
    user_id integer NOT NULL,
    last_name text NOT NULL,
    first_name text NOT NULL,
    patronymic text,
    school_class_id integer NOT NULL
);


ALTER TABLE students OWNER TO ixabtpit;

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE students_id_seq OWNER TO ixabtpit;

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- Name: subjects; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE subjects (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE subjects OWNER TO ixabtpit;

--
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE subjects_id_seq OWNER TO ixabtpit;

--
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE subjects_id_seq OWNED BY subjects.id;


--
-- Name: teacher_subjects; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE teacher_subjects (
    id integer NOT NULL,
    teacher_id integer NOT NULL,
    subject_id integer NOT NULL,
    qualification text NOT NULL
);


ALTER TABLE teacher_subjects OWNER TO ixabtpit;

--
-- Name: teacher_subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE teacher_subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE teacher_subjects_id_seq OWNER TO ixabtpit;

--
-- Name: teacher_subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE teacher_subjects_id_seq OWNED BY teacher_subjects.id;


--
-- Name: teachers; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE teachers (
    id integer NOT NULL,
    user_id integer NOT NULL,
    last_name text NOT NULL,
    first_name text NOT NULL,
    patronymic text,
    school_id integer NOT NULL,
    min_class_number integer NOT NULL,
    max_class_number integer NOT NULL
);


ALTER TABLE teachers OWNER TO ixabtpit;

--
-- Name: teachers_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE teachers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE teachers_id_seq OWNER TO ixabtpit;

--
-- Name: teachers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE teachers_id_seq OWNED BY teachers.id;


--
-- Name: template_variants; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE template_variants (
    id integer NOT NULL,
    variant character varying(30) NOT NULL,
    template_id integer NOT NULL
);


ALTER TABLE template_variants OWNER TO ixabtpit;

--
-- Name: template_variants_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE template_variants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE template_variants_id_seq OWNER TO ixabtpit;

--
-- Name: template_variants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE template_variants_id_seq OWNED BY template_variants.id;


--
-- Name: test_templates; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE test_templates (
    id integer NOT NULL,
    topic text,
    description text NOT NULL,
    class_number integer NOT NULL,
    subject_id integer NOT NULL,
    difficulty character varying(30) NOT NULL,
    creation_date date NOT NULL,
    status character(30)
);


ALTER TABLE test_templates OWNER TO ixabtpit;

--
-- Name: test_templates_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE test_templates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE test_templates_id_seq OWNER TO ixabtpit;

--
-- Name: test_templates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE test_templates_id_seq OWNED BY test_templates.id;


--
-- Name: tests; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE tests (
    id integer NOT NULL,
    test_template_id integer NOT NULL,
    owner_id integer,
    school_class_id integer,
    start_date_time timestamp without time zone,
    verification_deadline timestamp without time zone,
    status test_status,
    description character varying(100)
);


ALTER TABLE tests OWNER TO ixabtpit;

--
-- Name: tests_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE tests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tests_id_seq OWNER TO ixabtpit;

--
-- Name: tests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE tests_id_seq OWNED BY tests.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE users (
    id integer NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    role role NOT NULL,
    registration_date date NOT NULL,
    last_login_date date NOT NULL
);


ALTER TABLE users OWNER TO ixabtpit;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO ixabtpit;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: verification_pages; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE verification_pages (
    id integer NOT NULL,
    verification_result_id integer NOT NULL,
    file_url text NOT NULL
);


ALTER TABLE verification_pages OWNER TO ixabtpit;

--
-- Name: verification_pages_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE verification_pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE verification_pages_id_seq OWNER TO ixabtpit;

--
-- Name: verification_pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE verification_pages_id_seq OWNED BY verification_pages.id;


--
-- Name: verification_results; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE verification_results (
    id integer NOT NULL,
    date_time timestamp without time zone NOT NULL,
    comment text NOT NULL,
    mark integer NOT NULL,
    work_id integer NOT NULL,
    verifier_id integer NOT NULL
);


ALTER TABLE verification_results OWNER TO ixabtpit;

--
-- Name: verification_results_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE verification_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE verification_results_id_seq OWNER TO ixabtpit;

--
-- Name: verification_results_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE verification_results_id_seq OWNED BY verification_results.id;


--
-- Name: work_pages; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE work_pages (
    id integer NOT NULL,
    work_id integer NOT NULL,
    file_url text NOT NULL
);


ALTER TABLE work_pages OWNER TO ixabtpit;

--
-- Name: work_pages_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE work_pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE work_pages_id_seq OWNER TO ixabtpit;

--
-- Name: work_pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE work_pages_id_seq OWNED BY work_pages.id;


--
-- Name: works; Type: TABLE; Schema: public; Owner: ixabtpit
--

CREATE TABLE works (
    id integer NOT NULL,
    test_id integer NOT NULL,
    verifier_id integer,
    student_id integer NOT NULL,
    status work_status NOT NULL,
    appeal_comment text,
    verification_deadline timestamp without time zone,
    student_presence boolean DEFAULT true
);


ALTER TABLE works OWNER TO ixabtpit;

--
-- Name: works_id_seq; Type: SEQUENCE; Schema: public; Owner: ixabtpit
--

CREATE SEQUENCE works_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE works_id_seq OWNER TO ixabtpit;

--
-- Name: works_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ixabtpit
--

ALTER SEQUENCE works_id_seq OWNED BY works.id;


--
-- Name: city id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY city ALTER COLUMN id SET DEFAULT nextval('city_id_seq'::regclass);


--
-- Name: question_verification_criterions id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY question_verification_criterions ALTER COLUMN id SET DEFAULT nextval('question_verification_criteries_id_seq'::regclass);


--
-- Name: questions id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY questions ALTER COLUMN id SET DEFAULT nextval('questions_id_seq'::regclass);


--
-- Name: region id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY region ALTER COLUMN id SET DEFAULT nextval('region_id_seq'::regclass);


--
-- Name: school_classes id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY school_classes ALTER COLUMN id SET DEFAULT nextval('school_classes_id_seq'::regclass);


--
-- Name: school_types id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY school_types ALTER COLUMN id SET DEFAULT nextval('school_types_id_seq'::regclass);


--
-- Name: schools id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY schools ALTER COLUMN id SET DEFAULT nextval('schools_id_seq'::regclass);


--
-- Name: students id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- Name: subjects id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY subjects ALTER COLUMN id SET DEFAULT nextval('subjects_id_seq'::regclass);


--
-- Name: teacher_subjects id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teacher_subjects ALTER COLUMN id SET DEFAULT nextval('teacher_subjects_id_seq'::regclass);


--
-- Name: teachers id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teachers ALTER COLUMN id SET DEFAULT nextval('teachers_id_seq'::regclass);


--
-- Name: template_variants id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY template_variants ALTER COLUMN id SET DEFAULT nextval('template_variants_id_seq'::regclass);


--
-- Name: test_templates id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY test_templates ALTER COLUMN id SET DEFAULT nextval('test_templates_id_seq'::regclass);


--
-- Name: tests id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY tests ALTER COLUMN id SET DEFAULT nextval('tests_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: verification_pages id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_pages ALTER COLUMN id SET DEFAULT nextval('verification_pages_id_seq'::regclass);


--
-- Name: verification_results id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_results ALTER COLUMN id SET DEFAULT nextval('verification_results_id_seq'::regclass);


--
-- Name: work_pages id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY work_pages ALTER COLUMN id SET DEFAULT nextval('work_pages_id_seq'::regclass);


--
-- Name: works id; Type: DEFAULT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY works ALTER COLUMN id SET DEFAULT nextval('works_id_seq'::regclass);


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY city (id, region_id, name) FROM stdin;
1	1	Майкоп
\.


--
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('city_id_seq', 1, true);


--
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('question_verification_criteries_id_seq', 13, true);


--
-- Data for Name: question_verification_criterions; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY question_verification_criterions (id, question_id, criterion) FROM stdin;
1	2	вариант 2 вопрос 1 критерий 1
2	2	вариант 2 вопрос 1 критерий 2
3	3	вариант 1 вопрос 1 критерий 1
4	3	вариант 1 вопрос 1 критерий 2
5	4	вариант 1 вопрос 2 критерий 1
6	4	вариант 1 вопрос 2 критерий 2
7	6	ученик ответил на все вопросы
8	8	Ппц2
9	8	Конец
10	15	критерий 333
11	16	критерий 1
12	17	ученик ответил на все вопросы
13	18	ученик ответил не на все вопросы
\.


--
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY questions (id, template_variant_id, question, answer) FROM stdin;
1	1	вопрос 2.1	ответ 2.2
2	2	вариант 2 вопрос 1 	вариант 2 ответ 1
3	3	вариант 1 вопрос 1 	вариант 1 ответ 1
4	3	вариант 1 вопрос 2	вариант 1 ответ 2
5	4	Вопрос по уравнениям	ответ ученика
6	5	Вопрос по площадям объектов	ответ ученика 
7	5	Вопрос по площадям объектов 2	ответ ученика  2
8	6	Вопрос	О вопросе
9	7	Вопрос 1	Ответ 2
10	8	Вопрос 1	Ответ 2
11	10	Вопрос 1	Ответ 2
12	9	Вопрос 1	Ответ 2
13	11	Вопрос 1	Ответ 2
14	12	Вопрос 1	Ответ 2
15	13	вопрос 111	ответ 222
16	14	Вопрос 1	ответ 1
17	15	Вопрос по площадям объектов	ответ ученика 
18	15	Вопрос по площадям объектов 2	ответ ученика  2
\.


--
-- Name: questions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('questions_id_seq', 18, true);


--
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY region (id, num, name) FROM stdin;
1	1	Республика Адыгея
\.


--
-- Name: region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('region_id_seq', 1, true);


--
-- Data for Name: registration_url; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY registration_url (url, role, school_id, school_class_id) FROM stdin;
abcdef	admin	\N	\N
\.


--
-- Data for Name: school_classes; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY school_classes (id, number, name, school_id) FROM stdin;
3	11	А	2
6	10	Б	2
7	9	А	2
8	9	Б	2
4	11	Б	2
5	10	А	2
\.


--
-- Name: school_classes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('school_classes_id_seq', 8, true);


--
-- Data for Name: school_types; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY school_types (id, type_name) FROM stdin;
1	СОШ
2	Лицей
\.


--
-- Name: school_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('school_types_id_seq', 2, true);


--
-- Data for Name: schools; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY schools (id, name, region, city, school_type_id, city_id) FROM stdin;
2	СОШ1	Краснодарский край	Сочи	1	\N
4	СОШ №1	Республика Адыгея	Майкоп	1	1
\.


--
-- Name: schools_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('schools_id_seq', 4, true);


--
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY students (id, user_id, last_name, first_name, patronymic, school_class_id) FROM stdin;
1	2	Петров	Петр	Петрович	3
2	3	Иванов	Иван	Иванович	3
3	4	Михайловна	Мария	Сергеевна	3
\.


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('students_id_seq', 3, true);


--
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY subjects (id, name) FROM stdin;
1	Физика
2	Математика
3	Литература
\.


--
-- Name: subjects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('subjects_id_seq', 3, true);


--
-- Data for Name: teacher_subjects; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY teacher_subjects (id, teacher_id, subject_id, qualification) FROM stdin;
\.


--
-- Name: teacher_subjects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('teacher_subjects_id_seq', 1, false);


--
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY teachers (id, user_id, last_name, first_name, patronymic, school_id, min_class_number, max_class_number) FROM stdin;
4	1	Бородач	Снежана	Денисовна	2	1	11
\.


--
-- Name: teachers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('teachers_id_seq', 4, true);


--
-- Data for Name: template_variants; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY template_variants (id, variant, template_id) FROM stdin;
1	Вариант №2	3
2	Вариант №2	6
3	Вариант №1	6
4	Вариант №1	7
5	Вариант №1	8
6	Вариант №1	9
7	Вариант №1	11
8	Вариант №1	12
9	Вариант №1	13
10	Вариант №1	14
11	Вариант №1	14
12	Вариант №1	13
13	Вариант №2	15
14	Вариант №1	15
15	Р’Р°СЂРёР°РЅС‚ в„–1	16
\.


--
-- Name: template_variants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('template_variants_id_seq', 15, true);


--
-- Data for Name: test_templates; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY test_templates (id, topic, description, class_number, subject_id, difficulty, creation_date, status) FROM stdin;
3	Уравнения	шаблон 3	11	2	Легкая	2017-10-15	\N
5	Графики	шаблон 3	11	2	Легкая	2017-10-15	\N
7	Дифференциалы	Контрольная на тему...	9	2	Легкая	2017-10-17	\N
6	Тройной интеграл	Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной Описание контрольной 	11	2	Легкая	2017-10-16	\N
11	Тема не раскрыта	описание	11	1	Легкая	2017-10-20	\N
9	Искусство за 500	Контрольная на тему...	11	2	Легкая	2017-10-17	disabled                      
12	Тема не раскрыта	описание	11	1	Легкая	2017-10-20	disabled                      
13	Тема не раскрыта	описание	11	1	Легкая	2017-10-20	disabled                      
14	Тема не раскрыта	описание	11	1	Легкая	2017-10-20	disabled                      
4	Уравнения ч.2	шаблон 3	10	1	Легкая	2017-10-15	disabled                      
15	Уравнения ч.2	описание	10	1	Легкая	2017-10-20	\N
8	Площади объектов	Контрольная на тему...	11	2	Легкая	2017-10-17	disabled                      
16	Площади объектов	РѕРїРёСЃР°РЅРёРµ	11	2	Легкая	2017-10-20	\N
\.


--
-- Name: test_templates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('test_templates_id_seq', 16, true);


--
-- Data for Name: tests; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY tests (id, test_template_id, owner_id, school_class_id, start_date_time, verification_deadline, status, description) FROM stdin;
6	11	4	4	2017-10-20 00:00:00	2017-10-20 00:00:00	new	\N
7	16	4	4	2017-10-20 00:00:00	2017-10-20 00:00:00	new	\N
8	16	4	3	2017-10-20 00:00:00	2017-10-20 00:00:00	new	Контрольная на тему сложения
5	6	4	3	\N	2017-10-27 00:00:00	uploaded	\N
\.


--
-- Name: tests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('tests_id_seq', 8, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY users (id, login, password, role, registration_date, last_login_date) FROM stdin;
1	1	1	teacher	2017-10-16	2017-10-16
2	2	2	student	2017-10-16	2017-10-16
3	3	3	student	2017-10-16	2017-10-16
4	4	4	student	2017-10-16	2017-10-16
5	admin	�>�"�ζ3-�(]b�	admin	2017-10-19	2017-10-19
6	admin	A‡тc<¬Ь€‹Ћ�lг[qв	admin	2017-10-20	2017-10-20
7	vadimjigulin	�';�/����\r�n\b�f	admin	2017-10-21	2017-10-21
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('users_id_seq', 7, true);


--
-- Data for Name: verification_pages; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY verification_pages (id, verification_result_id, file_url) FROM stdin;
\.


--
-- Name: verification_pages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('verification_pages_id_seq', 1, false);


--
-- Data for Name: verification_results; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY verification_results (id, date_time, comment, mark, work_id, verifier_id) FROM stdin;
\.


--
-- Name: verification_results_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('verification_results_id_seq', 1, false);


--
-- Data for Name: work_pages; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY work_pages (id, work_id, file_url) FROM stdin;
1	19	http://photographyinspired.com/media/2014/08/Best-top-desktop-food-wallpapers-hd-food-wallpaper-food-pictures-image-photo-7.jpg
2	19	http://wallpapers1.ru/fud/data/fud_224.jpg
3	19	http://funlib.ru/cimg/2014/101902/5741133
4	20	http://www.italiaonaplate.com/wp-content/uploads/2017/07/under-construction-2408062_1920.png
\.


--
-- Name: work_pages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('work_pages_id_seq', 4, true);


--
-- Data for Name: works; Type: TABLE DATA; Schema: public; Owner: ixabtpit
--

COPY works (id, test_id, verifier_id, student_id, status, appeal_comment, verification_deadline, student_presence) FROM stdin;
21	5	\N	3	new	\N	\N	t
20	5	\N	2	confirmed	\N	\N	t
19	5	\N	1	uploaded	\N	\N	t
\.


--
-- Name: works_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ixabtpit
--

SELECT pg_catalog.setval('works_id_seq', 21, true);


--
-- Name: city city_id_pk; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_id_pk PRIMARY KEY (id);


--
-- Name: question_verification_criterions question_verification_criteries_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY question_verification_criterions
    ADD CONSTRAINT question_verification_criteries_pkey PRIMARY KEY (id);


--
-- Name: questions questions_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT questions_pkey PRIMARY KEY (id);


--
-- Name: region region_id_pk; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY region
    ADD CONSTRAINT region_id_pk PRIMARY KEY (id);


--
-- Name: registration_url registration_url_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY registration_url
    ADD CONSTRAINT registration_url_pkey PRIMARY KEY (url);


--
-- Name: school_classes school_classes_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY school_classes
    ADD CONSTRAINT school_classes_pkey PRIMARY KEY (id);


--
-- Name: school_types school_types_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY school_types
    ADD CONSTRAINT school_types_pkey PRIMARY KEY (id);


--
-- Name: schools schools_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_pkey PRIMARY KEY (id);


--
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- Name: teacher_subjects teacher_subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_pkey PRIMARY KEY (id);


--
-- Name: teachers teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (id);


--
-- Name: template_variants template_variants_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY template_variants
    ADD CONSTRAINT template_variants_pkey PRIMARY KEY (id);


--
-- Name: test_templates test_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY test_templates
    ADD CONSTRAINT test_templates_pkey PRIMARY KEY (id);


--
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: verification_pages verification_pages_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_pages
    ADD CONSTRAINT verification_pages_pkey PRIMARY KEY (id);


--
-- Name: verification_results verification_results_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_pkey PRIMARY KEY (id);


--
-- Name: work_pages work_pages_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY work_pages
    ADD CONSTRAINT work_pages_pkey PRIMARY KEY (id);


--
-- Name: works works_pkey; Type: CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_pkey PRIMARY KEY (id);


--
-- Name: registration_url_url_uindex; Type: INDEX; Schema: public; Owner: ixabtpit
--

CREATE UNIQUE INDEX registration_url_url_uindex ON registration_url USING btree (url);


--
-- Name: city city_region_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_region_id_fk FOREIGN KEY (region_id) REFERENCES region(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: question_verification_criterions question_verification_criteries_questions_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY question_verification_criterions
    ADD CONSTRAINT question_verification_criteries_questions_fkey FOREIGN KEY (question_id) REFERENCES questions(id);


--
-- Name: questions questions_template_variant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT questions_template_variant_id_fkey FOREIGN KEY (template_variant_id) REFERENCES template_variants(id);


--
-- Name: registration_url registration_url_school_classes_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY registration_url
    ADD CONSTRAINT registration_url_school_classes_id_fk FOREIGN KEY (school_class_id) REFERENCES school_classes(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: registration_url registration_url_schools_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY registration_url
    ADD CONSTRAINT registration_url_schools_id_fk FOREIGN KEY (school_id) REFERENCES schools(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: school_classes school_classes_school_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY school_classes
    ADD CONSTRAINT school_classes_school_id_fkey FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- Name: schools schools_city_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_city_id_fk FOREIGN KEY (city_id) REFERENCES city(id);


--
-- Name: schools schools_school_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_school_type_id_fkey FOREIGN KEY (school_type_id) REFERENCES school_types(id);


--
-- Name: students students_school_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_school_class_id_fkey FOREIGN KEY (school_class_id) REFERENCES school_classes(id);


--
-- Name: students students_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: students students_user_id_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_user_id_fkey1 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: teacher_subjects teacher_subjects_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- Name: teacher_subjects teacher_subjects_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_teacher_id_fkey FOREIGN KEY (teacher_id) REFERENCES teachers(id);


--
-- Name: teachers teachers_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_id_fkey FOREIGN KEY (id) REFERENCES users(id);


--
-- Name: teachers teachers_schools_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_schools_id_fk FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- Name: template_variants template; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY template_variants
    ADD CONSTRAINT template FOREIGN KEY (template_id) REFERENCES test_templates(id);


--
-- Name: test_templates test_templates_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY test_templates
    ADD CONSTRAINT test_templates_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- Name: tests tests_owner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES teachers(id);


--
-- Name: tests tests_test_template_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_test_template_id_fkey FOREIGN KEY (test_template_id) REFERENCES test_templates(id);


--
-- Name: verification_pages verification_pages_verification_result_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_pages
    ADD CONSTRAINT verification_pages_verification_result_id_fkey FOREIGN KEY (verification_result_id) REFERENCES verification_results(id);


--
-- Name: verification_results verification_results_verifier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_verifier_id_fkey FOREIGN KEY (verifier_id) REFERENCES teachers(id);


--
-- Name: verification_results verification_results_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_work_id_fkey FOREIGN KEY (work_id) REFERENCES works(id);


--
-- Name: work_pages work_pages_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY work_pages
    ADD CONSTRAINT work_pages_work_id_fkey FOREIGN KEY (work_id) REFERENCES works(id);


--
-- Name: works works_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_student_id_fkey FOREIGN KEY (student_id) REFERENCES students(id);


--
-- Name: works works_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_test_id_fkey FOREIGN KEY (test_id) REFERENCES tests(id);


--
-- Name: works works_verifier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ixabtpit
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_verifier_id_fkey FOREIGN KEY (verifier_id) REFERENCES teachers(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

