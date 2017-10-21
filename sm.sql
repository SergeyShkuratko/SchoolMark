-- README
--
-- to restore:
--   psql dbName < sm.sql
-- to dump:
--   pg_dump dbName > sm.sql


--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE role AS ENUM (
    'director',
    'teacher',
    'student',
    'admin'
);


ALTER TYPE role OWNER TO postgres;

--
-- Name: test_status; Type: TYPE; Schema: public; Owner: postgres
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


ALTER TYPE test_status OWNER TO postgres;

--
-- Name: work_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE work_status AS ENUM (
    'new',
    'in_progressappointed',
    'on_verification',
    'verified',
    'confirmed',
    'reverification'
);


ALTER TYPE work_status OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE city (
    id integer NOT NULL,
    region_id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE city OWNER TO postgres;

--
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE city_id_seq OWNER TO postgres;

--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE city_id_seq OWNED BY city.id;


--
-- Name: question_verification_criteries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE question_verification_criteries (
    id integer NOT NULL,
    question_id integer NOT NULL,
    criteria text NOT NULL
);


ALTER TABLE question_verification_criteries OWNER TO postgres;

--
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE question_verification_criteries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE question_verification_criteries_id_seq OWNER TO postgres;

--
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE question_verification_criteries_id_seq OWNED BY question_verification_criteries.id;


--
-- Name: questions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE questions (
    id integer NOT NULL,
    test_template_id integer NOT NULL,
    question text NOT NULL,
    answer text NOT NULL,
    criteria text NOT NULL
);


ALTER TABLE questions OWNER TO postgres;

--
-- Name: questions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE questions_id_seq OWNER TO postgres;

--
-- Name: questions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE questions_id_seq OWNED BY questions.id;


--
-- Name: region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE region (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE region OWNER TO postgres;

--
-- Name: region_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE region_id_seq OWNER TO postgres;

--
-- Name: region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE region_id_seq OWNED BY region.id;


--
-- Name: school_class; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE school_class (
    id integer NOT NULL,
    number integer NOT NULL,
    name text NOT NULL,
    school_id integer NOT NULL
);


ALTER TABLE school_class OWNER TO postgres;

--
-- Name: school_class_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE school_class_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE school_class_id_seq OWNER TO postgres;

--
-- Name: school_class_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE school_class_id_seq OWNED BY school_class.id;


--
-- Name: school_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE school_types (
    id integer NOT NULL,
    type_name text NOT NULL
);


ALTER TABLE school_types OWNER TO postgres;

--
-- Name: school_types_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE school_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE school_types_id_seq OWNER TO postgres;

--
-- Name: school_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE school_types_id_seq OWNED BY school_types.id;


--
-- Name: schools; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE schools (
    id integer NOT NULL,
    name text NOT NULL,
    school_type_id integer NOT NULL,
    city_id integer NOT NULL
);


ALTER TABLE schools OWNER TO postgres;

--
-- Name: schools_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schools_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE schools_id_seq OWNER TO postgres;

--
-- Name: schools_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schools_id_seq OWNED BY schools.id;


--
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE students (
    id integer NOT NULL,
    user_id integer NOT NULL,
    last_name text NOT NULL,
    first_name text NOT NULL,
    patronymic text,
    school_class_id integer NOT NULL
);


ALTER TABLE students OWNER TO postgres;

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE students_id_seq OWNER TO postgres;

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- Name: subjects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE subjects (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE subjects OWNER TO postgres;

--
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE subjects_id_seq OWNER TO postgres;

--
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE subjects_id_seq OWNED BY subjects.id;


--
-- Name: teacher_subjects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE teacher_subjects (
    id integer NOT NULL,
    teacher_id integer NOT NULL,
    subject_id integer NOT NULL,
    qualification text NOT NULL
);


ALTER TABLE teacher_subjects OWNER TO postgres;

--
-- Name: teacher_subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE teacher_subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE teacher_subjects_id_seq OWNER TO postgres;

--
-- Name: teacher_subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE teacher_subjects_id_seq OWNED BY teacher_subjects.id;


--
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE teachers OWNER TO postgres;

--
-- Name: teachers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE teachers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE teachers_id_seq OWNER TO postgres;

--
-- Name: teachers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE teachers_id_seq OWNED BY teachers.id;


--
-- Name: test_templates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE test_templates (
    id integer NOT NULL,
    topic text NOT NULL,
    description text NOT NULL,
    class_number integer NOT NULL,
    subject_id integer NOT NULL
);


ALTER TABLE test_templates OWNER TO postgres;

--
-- Name: test_templates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE test_templates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE test_templates_id_seq OWNER TO postgres;

--
-- Name: test_templates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE test_templates_id_seq OWNED BY test_templates.id;


--
-- Name: tests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tests (
    id integer NOT NULL,
    test_template_id integer NOT NULL,
    owner_id integer NOT NULL,
    school_class_id integer NOT NULL,
    start_date_time timestamp without time zone NOT NULL,
    verification_deadline timestamp without time zone NOT NULL,
    status test_status NOT NULL
);


ALTER TABLE tests OWNER TO postgres;

--
-- Name: tests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tests_id_seq OWNER TO postgres;

--
-- Name: tests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tests_id_seq OWNED BY tests.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    role role NOT NULL,
    registration_date date NOT NULL,
    last_login_date date NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: verification_pages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE verification_pages (
    id integer NOT NULL,
    verification_result_id integer NOT NULL,
    file_url text NOT NULL
);


ALTER TABLE verification_pages OWNER TO postgres;

--
-- Name: verification_pages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE verification_pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE verification_pages_id_seq OWNER TO postgres;

--
-- Name: verification_pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE verification_pages_id_seq OWNED BY verification_pages.id;


--
-- Name: verification_results; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE verification_results (
    id integer NOT NULL,
    date_time timestamp without time zone NOT NULL,
    comment text NOT NULL,
    mark integer NOT NULL,
    work_id integer NOT NULL,
    verifier_id integer NOT NULL
);


ALTER TABLE verification_results OWNER TO postgres;

--
-- Name: verification_results_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE verification_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE verification_results_id_seq OWNER TO postgres;

--
-- Name: verification_results_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE verification_results_id_seq OWNED BY verification_results.id;


--
-- Name: work_pages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE work_pages (
    id integer NOT NULL,
    work_id integer NOT NULL,
    file_url text NOT NULL
);


ALTER TABLE work_pages OWNER TO postgres;

--
-- Name: work_pages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE work_pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE work_pages_id_seq OWNER TO postgres;

--
-- Name: work_pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE work_pages_id_seq OWNED BY work_pages.id;


--
-- Name: works; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE works (
    id integer NOT NULL,
    test_id integer NOT NULL,
    verifier_id integer NOT NULL,
    student_id integer NOT NULL,
    status work_status NOT NULL,
    appeal_comment text,
    verification_deadline timestamp without time zone NOT NULL
);


ALTER TABLE works OWNER TO postgres;

--
-- Name: works_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE works_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE works_id_seq OWNER TO postgres;

--
-- Name: works_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE works_id_seq OWNED BY works.id;


--
-- Name: city id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY city ALTER COLUMN id SET DEFAULT nextval('city_id_seq'::regclass);


--
-- Name: question_verification_criteries id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY question_verification_criteries ALTER COLUMN id SET DEFAULT nextval('question_verification_criteries_id_seq'::regclass);


--
-- Name: questions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY questions ALTER COLUMN id SET DEFAULT nextval('questions_id_seq'::regclass);


--
-- Name: region id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY region ALTER COLUMN id SET DEFAULT nextval('region_id_seq'::regclass);


--
-- Name: school_class id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY school_class ALTER COLUMN id SET DEFAULT nextval('school_class_id_seq'::regclass);


--
-- Name: school_types id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY school_types ALTER COLUMN id SET DEFAULT nextval('school_types_id_seq'::regclass);


--
-- Name: schools id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schools ALTER COLUMN id SET DEFAULT nextval('schools_id_seq'::regclass);


--
-- Name: students id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- Name: subjects id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subjects ALTER COLUMN id SET DEFAULT nextval('subjects_id_seq'::regclass);


--
-- Name: teacher_subjects id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teacher_subjects ALTER COLUMN id SET DEFAULT nextval('teacher_subjects_id_seq'::regclass);


--
-- Name: teachers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teachers ALTER COLUMN id SET DEFAULT nextval('teachers_id_seq'::regclass);


--
-- Name: test_templates id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY test_templates ALTER COLUMN id SET DEFAULT nextval('test_templates_id_seq'::regclass);


--
-- Name: tests id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tests ALTER COLUMN id SET DEFAULT nextval('tests_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: verification_pages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_pages ALTER COLUMN id SET DEFAULT nextval('verification_pages_id_seq'::regclass);


--
-- Name: verification_results id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_results ALTER COLUMN id SET DEFAULT nextval('verification_results_id_seq'::regclass);


--
-- Name: work_pages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY work_pages ALTER COLUMN id SET DEFAULT nextval('work_pages_id_seq'::regclass);


--
-- Name: works id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY works ALTER COLUMN id SET DEFAULT nextval('works_id_seq'::regclass);


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY city (id, region_id, name) FROM stdin;
1	1	Сругут
2	2	Минеральные Воды
3	2	Кисловодск
4	2	Пятигорск
5	2	Лермонтов\n
6	2	Георгиевск
\.


--
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('city_id_seq', 6, true);


--
-- Data for Name: question_verification_criteries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY question_verification_criteries (id, question_id, criteria) FROM stdin;
\.


--
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('question_verification_criteries_id_seq', 1, false);


--
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY questions (id, test_template_id, question, answer, criteria) FROM stdin;
\.


--
-- Name: questions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('questions_id_seq', 1, false);


--
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY region (id, name) FROM stdin;
1	Ханты мансийский автономный округ - Югра
2	Ставропольский край
\.


--
-- Name: region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('region_id_seq', 1, true);


--
-- Data for Name: school_class; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY school_class (id, number, name, school_id) FROM stdin;
1	11	А	1
\.


--
-- Name: school_class_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('school_class_id_seq', 2, true);


--
-- Data for Name: school_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY school_types (id, type_name) FROM stdin;
1	Углубленное изучение поверхностных теорий
\.


--
-- Name: school_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('school_types_id_seq', 1, true);


--
-- Data for Name: schools; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY schools (id, name, school_type_id, city_id) FROM stdin;
1	СОШ №1	1	1
\.


--
-- Name: schools_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('schools_id_seq', 2, true);


--
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY students (id, user_id, last_name, first_name, patronymic, school_class_id) FROM stdin;
\.


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('students_id_seq', 1, false);


--
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY subjects (id, name) FROM stdin;
1	География
2	Математика
3	Химия
4	Русский язык
5	Биология
\.


--
-- Name: subjects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('subjects_id_seq', 5, true);


--
-- Data for Name: teacher_subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY teacher_subjects (id, teacher_id, subject_id, qualification) FROM stdin;
\.


--
-- Name: teacher_subjects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('teacher_subjects_id_seq', 1, false);


--
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY teachers (id, user_id, last_name, first_name, patronymic, school_id, min_class_number, max_class_number) FROM stdin;
2	2	Постукова	Антонина	Петровна	1	5	11
1	1	Потапова	Элеанора	Ивановна	1	5	11
4	4	Чмырёва	Елизавета	Артуровна	1	5	11
3	3	Веселухина	Марина	Федоровна	1	5	11
\.


--
-- Name: teachers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('teachers_id_seq', 6, true);


--
-- Data for Name: test_templates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY test_templates (id, topic, description, class_number, subject_id) FROM stdin;
1	Срез №4	Описание среза №4	10	1
\.


--
-- Name: test_templates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('test_templates_id_seq', 1, true);


--
-- Data for Name: tests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tests (id, test_template_id, owner_id, school_class_id, start_date_time, verification_deadline, status) FROM stdin;
1	1	4	1	2017-10-17 08:00:00	2017-11-11 22:00:00	uploaded
\.


--
-- Name: tests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tests_id_seq', 1, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, login, password, role, registration_date, last_login_date) FROM stdin;
1	t2	р_XУќЛ ‡йщ*$	teacher	2017-01-01	2017-01-01
6	admin	р_XУќЛ ‡йщ*$	admin	2017-01-01	2017-01-01
2	t1	р_XУќЛ ‡йщ*$	teacher	2017-01-01	2017-01-01
3	t3	р_XУќЛ ‡йщ*$	teacher	2017-01-01	2017-01-01
4	director	р_XУќЛ ‡йщ*$	director	2017-01-01	2017-01-01
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 6, true);


--
-- Data for Name: verification_pages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY verification_pages (id, verification_result_id, file_url) FROM stdin;
\.


--
-- Name: verification_pages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('verification_pages_id_seq', 1, false);


--
-- Data for Name: verification_results; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY verification_results (id, date_time, comment, mark, work_id, verifier_id) FROM stdin;
\.


--
-- Name: verification_results_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('verification_results_id_seq', 1, false);


--
-- Data for Name: work_pages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY work_pages (id, work_id, file_url) FROM stdin;
\.


--
-- Name: work_pages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('work_pages_id_seq', 1, false);


--
-- Data for Name: works; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY works (id, test_id, verifier_id, student_id, status, appeal_comment, verification_deadline) FROM stdin;
\.


--
-- Name: works_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('works_id_seq', 1, true);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- Name: question_verification_criteries question_verification_criteries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY question_verification_criteries
    ADD CONSTRAINT question_verification_criteries_pkey PRIMARY KEY (id);


--
-- Name: questions questions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT questions_pkey PRIMARY KEY (id);


--
-- Name: region region_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY region
    ADD CONSTRAINT region_pkey PRIMARY KEY (id);


--
-- Name: school_class school_class_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY school_class
    ADD CONSTRAINT school_class_pkey PRIMARY KEY (id);


--
-- Name: school_types school_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY school_types
    ADD CONSTRAINT school_types_pkey PRIMARY KEY (id);


--
-- Name: schools schools_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_pkey PRIMARY KEY (id);


--
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- Name: teacher_subjects teacher_subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_pkey PRIMARY KEY (id);


--
-- Name: teachers teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (id);


--
-- Name: test_templates test_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY test_templates
    ADD CONSTRAINT test_templates_pkey PRIMARY KEY (id);


--
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: verification_pages verification_pages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_pages
    ADD CONSTRAINT verification_pages_pkey PRIMARY KEY (id);


--
-- Name: verification_results verification_results_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_pkey PRIMARY KEY (id);


--
-- Name: work_pages work_pages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY work_pages
    ADD CONSTRAINT work_pages_pkey PRIMARY KEY (id);


--
-- Name: works works_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_pkey PRIMARY KEY (id);


--
-- Name: fki_teachers_school_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_teachers_school_id_fkey ON teachers USING btree (school_id);


--
-- Name: questions questions_test_template_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT questions_test_template_id_fkey FOREIGN KEY (test_template_id) REFERENCES test_templates(id);


--
-- Name: school_class school_class_school_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY school_class
    ADD CONSTRAINT school_class_school_id_fkey FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- Name: schools schools_school_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_school_type_id_fkey FOREIGN KEY (school_type_id) REFERENCES school_types(id);


--
-- Name: students students_school_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_school_class_id_fkey FOREIGN KEY (school_class_id) REFERENCES school_class(id);


--
-- Name: students students_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: students students_user_id_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_user_id_fkey1 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: teacher_subjects teacher_subjects_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- Name: teacher_subjects teacher_subjects_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_teacher_id_fkey FOREIGN KEY (teacher_id) REFERENCES teachers(id);


--
-- Name: teachers teachers_school_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_school_id_fkey FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- Name: test_templates test_templates_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY test_templates
    ADD CONSTRAINT test_templates_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- Name: tests tests_owner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES teachers(id);


--
-- Name: tests tests_test_template_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_test_template_id_fkey FOREIGN KEY (test_template_id) REFERENCES test_templates(id);


--
-- Name: verification_pages verification_pages_verification_result_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_pages
    ADD CONSTRAINT verification_pages_verification_result_id_fkey FOREIGN KEY (verification_result_id) REFERENCES verification_results(id);


--
-- Name: verification_results verification_results_verifier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_verifier_id_fkey FOREIGN KEY (verifier_id) REFERENCES teachers(id);


--
-- Name: verification_results verification_results_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_work_id_fkey FOREIGN KEY (work_id) REFERENCES works(id);


--
-- Name: work_pages work_pages_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY work_pages
    ADD CONSTRAINT work_pages_work_id_fkey FOREIGN KEY (work_id) REFERENCES works(id);


--
-- Name: works works_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_student_id_fkey FOREIGN KEY (student_id) REFERENCES students(id);


--
-- Name: works works_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_test_id_fkey FOREIGN KEY (test_id) REFERENCES tests(id);


--
-- Name: works works_verifier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_verifier_id_fkey FOREIGN KEY (verifier_id) REFERENCES teachers(id);


--
-- PostgreSQL database dump complete
--

