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

-- Started on 2017-10-21 20:00:59 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 3079 OID 1907149)
-- Name: plperl; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plperl WITH SCHEMA pg_catalog;


--
-- TOC entry 2783 (class 0 OID 0)
-- Dependencies: 6
-- Name: EXTENSION plperl; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plperl IS 'PL/Perl procedural language';


--
-- TOC entry 1 (class 3079 OID 12655)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2784 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 3 (class 3079 OID 1906907)
-- Name: hstore; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS hstore WITH SCHEMA public;


--
-- TOC entry 2785 (class 0 OID 0)
-- Dependencies: 3
-- Name: EXTENSION hstore; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION hstore IS 'data type for storing sets of (key, value) pairs';


--
-- TOC entry 2 (class 3079 OID 16419)
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- TOC entry 2786 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pg_stat_statements IS 'track execution statistics of all SQL statements executed';


--
-- TOC entry 5 (class 3079 OID 1907072)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- TOC entry 2787 (class 0 OID 0)
-- Dependencies: 5
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- TOC entry 4 (class 3079 OID 1907030)
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- TOC entry 2788 (class 0 OID 0)
-- Dependencies: 4
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


SET search_path = public, pg_catalog;

--
-- TOC entry 701 (class 1247 OID 26273894)
-- Name: role; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE role AS ENUM (
    'director',
    'teacher',
    'student',
    'admin'
);


--
-- TOC entry 756 (class 1247 OID 26274031)
-- Name: test_status; Type: TYPE; Schema: public; Owner: -
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


--
-- TOC entry 763 (class 1247 OID 26274054)
-- Name: work_status; Type: TYPE; Schema: public; Owner: -
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


SET default_with_oids = false;

--
-- TOC entry 222 (class 1259 OID 26464064)
-- Name: city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE city (
    id integer NOT NULL,
    region_id integer NOT NULL,
    name character varying(100) NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 26464062)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2789 (class 0 OID 0)
-- Dependencies: 221
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE city_id_seq OWNED BY city.id;


--
-- TOC entry 206 (class 1259 OID 26274009)
-- Name: question_verification_criterions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE question_verification_criterions (
    id integer NOT NULL,
    question_id integer NOT NULL,
    criterion text NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 26274007)
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE question_verification_criteries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2790 (class 0 OID 0)
-- Dependencies: 205
-- Name: question_verification_criteries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE question_verification_criteries_id_seq OWNED BY question_verification_criterions.id;


--
-- TOC entry 204 (class 1259 OID 26273998)
-- Name: questions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE questions (
    id integer NOT NULL,
    template_variant_id integer NOT NULL,
    question text NOT NULL,
    answer text NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 26273996)
-- Name: questions_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2791 (class 0 OID 0)
-- Dependencies: 203
-- Name: questions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE questions_id_seq OWNED BY questions.id;


--
-- TOC entry 224 (class 1259 OID 26464141)
-- Name: region; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE region (
    id integer NOT NULL,
    num integer,
    name character varying(100) NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 26464139)
-- Name: region_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2792 (class 0 OID 0)
-- Dependencies: 223
-- Name: region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE region_id_seq OWNED BY region.id;


--
-- TOC entry 226 (class 1259 OID 26517205)
-- Name: registration_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE registration_token (
    token character varying(16) NOT NULL,
    role role NOT NULL,
    school_id integer,
    school_class_id integer
);


--
-- TOC entry 225 (class 1259 OID 26466031)
-- Name: registration_url; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE registration_url (
    url character varying(13) NOT NULL,
    role role DEFAULT 'admin'::role NOT NULL,
    school_id integer,
    school_class_id integer
);


--
-- TOC entry 192 (class 1259 OID 26273931)
-- Name: school_classes; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE school_classes (
    id integer NOT NULL,
    number integer NOT NULL,
    name text NOT NULL,
    school_id integer NOT NULL
);


--
-- TOC entry 191 (class 1259 OID 26273929)
-- Name: school_classes_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE school_classes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2793 (class 0 OID 0)
-- Dependencies: 191
-- Name: school_classes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE school_classes_id_seq OWNED BY school_classes.id;


--
-- TOC entry 196 (class 1259 OID 26273953)
-- Name: school_types; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE school_types (
    id integer NOT NULL,
    type_name text NOT NULL
);


--
-- TOC entry 195 (class 1259 OID 26273951)
-- Name: school_types_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE school_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2794 (class 0 OID 0)
-- Dependencies: 195
-- Name: school_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE school_types_id_seq OWNED BY school_types.id;


--
-- TOC entry 194 (class 1259 OID 26273942)
-- Name: schools; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE schools (
    id integer NOT NULL,
    name text NOT NULL,
    region text NOT NULL,
    city text NOT NULL,
    school_type_id integer NOT NULL,
    city_id integer
);


--
-- TOC entry 193 (class 1259 OID 26273940)
-- Name: schools_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE schools_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2795 (class 0 OID 0)
-- Dependencies: 193
-- Name: schools_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE schools_id_seq OWNED BY schools.id;


--
-- TOC entry 190 (class 1259 OID 26273915)
-- Name: students; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE students (
    id integer NOT NULL,
    user_id integer NOT NULL,
    last_name text NOT NULL,
    first_name text NOT NULL,
    patronymic text,
    school_class_id integer NOT NULL
);


--
-- TOC entry 189 (class 1259 OID 26273913)
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2796 (class 0 OID 0)
-- Dependencies: 189
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- TOC entry 200 (class 1259 OID 26273976)
-- Name: subjects; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE subjects (
    id integer NOT NULL,
    name text NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 26273974)
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2797 (class 0 OID 0)
-- Dependencies: 199
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE subjects_id_seq OWNED BY subjects.id;


--
-- TOC entry 202 (class 1259 OID 26273987)
-- Name: teacher_subjects; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE teacher_subjects (
    id integer NOT NULL,
    teacher_id integer NOT NULL,
    subject_id integer NOT NULL,
    qualification text NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 26273985)
-- Name: teacher_subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE teacher_subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2798 (class 0 OID 0)
-- Dependencies: 201
-- Name: teacher_subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE teacher_subjects_id_seq OWNED BY teacher_subjects.id;


--
-- TOC entry 198 (class 1259 OID 26273965)
-- Name: teachers; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 197 (class 1259 OID 26273963)
-- Name: teachers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE teachers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2799 (class 0 OID 0)
-- Dependencies: 197
-- Name: teachers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE teachers_id_seq OWNED BY teachers.id;


--
-- TOC entry 220 (class 1259 OID 26382587)
-- Name: template_variants; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE template_variants (
    id integer NOT NULL,
    variant character varying(30) NOT NULL,
    template_id integer NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 26382585)
-- Name: template_variants_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE template_variants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2800 (class 0 OID 0)
-- Dependencies: 219
-- Name: template_variants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE template_variants_id_seq OWNED BY template_variants.id;


--
-- TOC entry 208 (class 1259 OID 26274021)
-- Name: test_templates; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 207 (class 1259 OID 26274019)
-- Name: test_templates_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE test_templates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2801 (class 0 OID 0)
-- Dependencies: 207
-- Name: test_templates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE test_templates_id_seq OWNED BY test_templates.id;


--
-- TOC entry 210 (class 1259 OID 26274047)
-- Name: tests; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 209 (class 1259 OID 26274045)
-- Name: tests_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2802 (class 0 OID 0)
-- Dependencies: 209
-- Name: tests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tests_id_seq OWNED BY tests.id;


--
-- TOC entry 188 (class 1259 OID 26273904)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE users (
    id integer NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    role role NOT NULL,
    registration_date date NOT NULL,
    last_login_date date NOT NULL
);


--
-- TOC entry 187 (class 1259 OID 26273902)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2803 (class 0 OID 0)
-- Dependencies: 187
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 218 (class 1259 OID 26274107)
-- Name: verification_pages; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE verification_pages (
    id integer NOT NULL,
    verification_result_id integer NOT NULL,
    file_url text NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 26274105)
-- Name: verification_pages_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE verification_pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2804 (class 0 OID 0)
-- Dependencies: 217
-- Name: verification_pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE verification_pages_id_seq OWNED BY verification_pages.id;


--
-- TOC entry 216 (class 1259 OID 26274092)
-- Name: verification_results; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE verification_results (
    id integer NOT NULL,
    date_time timestamp without time zone NOT NULL,
    comment text NOT NULL,
    mark integer NOT NULL,
    work_id integer NOT NULL,
    verifier_id integer NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 26274090)
-- Name: verification_results_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE verification_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2805 (class 0 OID 0)
-- Dependencies: 215
-- Name: verification_results_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE verification_results_id_seq OWNED BY verification_results.id;


--
-- TOC entry 214 (class 1259 OID 26274080)
-- Name: work_pages; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE work_pages (
    id integer NOT NULL,
    work_id integer NOT NULL,
    file_url text NOT NULL
);


--
-- TOC entry 213 (class 1259 OID 26274078)
-- Name: work_pages_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE work_pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2806 (class 0 OID 0)
-- Dependencies: 213
-- Name: work_pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE work_pages_id_seq OWNED BY work_pages.id;


--
-- TOC entry 212 (class 1259 OID 26274069)
-- Name: works; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 211 (class 1259 OID 26274067)
-- Name: works_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE works_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2807 (class 0 OID 0)
-- Dependencies: 211
-- Name: works_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE works_id_seq OWNED BY works.id;


--
-- TOC entry 2587 (class 2604 OID 26464067)
-- Name: city id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY city ALTER COLUMN id SET DEFAULT nextval('city_id_seq'::regclass);


--
-- TOC entry 2578 (class 2604 OID 26274012)
-- Name: question_verification_criterions id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY question_verification_criterions ALTER COLUMN id SET DEFAULT nextval('question_verification_criteries_id_seq'::regclass);


--
-- TOC entry 2577 (class 2604 OID 26274001)
-- Name: questions id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY questions ALTER COLUMN id SET DEFAULT nextval('questions_id_seq'::regclass);


--
-- TOC entry 2588 (class 2604 OID 26464144)
-- Name: region id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY region ALTER COLUMN id SET DEFAULT nextval('region_id_seq'::regclass);


--
-- TOC entry 2571 (class 2604 OID 26273934)
-- Name: school_classes id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY school_classes ALTER COLUMN id SET DEFAULT nextval('school_classes_id_seq'::regclass);


--
-- TOC entry 2573 (class 2604 OID 26273956)
-- Name: school_types id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY school_types ALTER COLUMN id SET DEFAULT nextval('school_types_id_seq'::regclass);


--
-- TOC entry 2572 (class 2604 OID 26273945)
-- Name: schools id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY schools ALTER COLUMN id SET DEFAULT nextval('schools_id_seq'::regclass);


--
-- TOC entry 2570 (class 2604 OID 26273918)
-- Name: students id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- TOC entry 2575 (class 2604 OID 26273979)
-- Name: subjects id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY subjects ALTER COLUMN id SET DEFAULT nextval('subjects_id_seq'::regclass);


--
-- TOC entry 2576 (class 2604 OID 26273990)
-- Name: teacher_subjects id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY teacher_subjects ALTER COLUMN id SET DEFAULT nextval('teacher_subjects_id_seq'::regclass);


--
-- TOC entry 2574 (class 2604 OID 26273968)
-- Name: teachers id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY teachers ALTER COLUMN id SET DEFAULT nextval('teachers_id_seq'::regclass);


--
-- TOC entry 2586 (class 2604 OID 26382590)
-- Name: template_variants id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY template_variants ALTER COLUMN id SET DEFAULT nextval('template_variants_id_seq'::regclass);


--
-- TOC entry 2579 (class 2604 OID 26274024)
-- Name: test_templates id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY test_templates ALTER COLUMN id SET DEFAULT nextval('test_templates_id_seq'::regclass);


--
-- TOC entry 2580 (class 2604 OID 26274050)
-- Name: tests id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tests ALTER COLUMN id SET DEFAULT nextval('tests_id_seq'::regclass);


--
-- TOC entry 2569 (class 2604 OID 26273907)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2585 (class 2604 OID 26274110)
-- Name: verification_pages id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_pages ALTER COLUMN id SET DEFAULT nextval('verification_pages_id_seq'::regclass);


--
-- TOC entry 2584 (class 2604 OID 26274095)
-- Name: verification_results id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_results ALTER COLUMN id SET DEFAULT nextval('verification_results_id_seq'::regclass);


--
-- TOC entry 2583 (class 2604 OID 26274083)
-- Name: work_pages id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY work_pages ALTER COLUMN id SET DEFAULT nextval('work_pages_id_seq'::regclass);


--
-- TOC entry 2582 (class 2604 OID 26274072)
-- Name: works id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY works ALTER COLUMN id SET DEFAULT nextval('works_id_seq'::regclass);


--
-- TOC entry 2625 (class 2606 OID 26464227)
-- Name: city city_id_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_id_pk PRIMARY KEY (id);


--
-- TOC entry 2609 (class 2606 OID 26274017)
-- Name: question_verification_criterions question_verification_criteries_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY question_verification_criterions
    ADD CONSTRAINT question_verification_criteries_pkey PRIMARY KEY (id);


--
-- TOC entry 2607 (class 2606 OID 26274006)
-- Name: questions questions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT questions_pkey PRIMARY KEY (id);


--
-- TOC entry 2627 (class 2606 OID 26464217)
-- Name: region region_id_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY region
    ADD CONSTRAINT region_id_pk PRIMARY KEY (id);


--
-- TOC entry 2632 (class 2606 OID 26517209)
-- Name: registration_token registration_token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY registration_token
    ADD CONSTRAINT registration_token_pkey PRIMARY KEY (token);


--
-- TOC entry 2629 (class 2606 OID 26466036)
-- Name: registration_url registration_url_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY registration_url
    ADD CONSTRAINT registration_url_pkey PRIMARY KEY (url);


--
-- TOC entry 2595 (class 2606 OID 26273939)
-- Name: school_classes school_classes_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY school_classes
    ADD CONSTRAINT school_classes_pkey PRIMARY KEY (id);


--
-- TOC entry 2599 (class 2606 OID 26273961)
-- Name: school_types school_types_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY school_types
    ADD CONSTRAINT school_types_pkey PRIMARY KEY (id);


--
-- TOC entry 2597 (class 2606 OID 26273950)
-- Name: schools schools_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_pkey PRIMARY KEY (id);


--
-- TOC entry 2593 (class 2606 OID 26273923)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- TOC entry 2603 (class 2606 OID 26273984)
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- TOC entry 2605 (class 2606 OID 26273995)
-- Name: teacher_subjects teacher_subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_pkey PRIMARY KEY (id);


--
-- TOC entry 2601 (class 2606 OID 26273973)
-- Name: teachers teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (id);


--
-- TOC entry 2623 (class 2606 OID 26382592)
-- Name: template_variants template_variants_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY template_variants
    ADD CONSTRAINT template_variants_pkey PRIMARY KEY (id);


--
-- TOC entry 2611 (class 2606 OID 26274029)
-- Name: test_templates test_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY test_templates
    ADD CONSTRAINT test_templates_pkey PRIMARY KEY (id);


--
-- TOC entry 2613 (class 2606 OID 26274052)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- TOC entry 2591 (class 2606 OID 26273912)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2621 (class 2606 OID 26274115)
-- Name: verification_pages verification_pages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_pages
    ADD CONSTRAINT verification_pages_pkey PRIMARY KEY (id);


--
-- TOC entry 2619 (class 2606 OID 26274100)
-- Name: verification_results verification_results_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_pkey PRIMARY KEY (id);


--
-- TOC entry 2617 (class 2606 OID 26274088)
-- Name: work_pages work_pages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY work_pages
    ADD CONSTRAINT work_pages_pkey PRIMARY KEY (id);


--
-- TOC entry 2615 (class 2606 OID 26274077)
-- Name: works works_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_pkey PRIMARY KEY (id);


--
-- TOC entry 2630 (class 1259 OID 26466047)
-- Name: registration_url_url_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX registration_url_url_uindex ON registration_url USING btree (url);


--
-- TOC entry 2633 (class 1259 OID 26517210)
-- Name: token_unique; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX token_unique ON registration_token USING btree (token);

ALTER TABLE registration_token CLUSTER ON token_unique;


--
-- TOC entry 2657 (class 2606 OID 26464221)
-- Name: city city_region_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_region_id_fk FOREIGN KEY (region_id) REFERENCES region(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2661 (class 2606 OID 26517216)
-- Name: registration_token fk_class_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY registration_token
    ADD CONSTRAINT fk_class_id FOREIGN KEY (school_class_id) REFERENCES school_classes(id);


--
-- TOC entry 2660 (class 2606 OID 26517211)
-- Name: registration_token fk_school_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY registration_token
    ADD CONSTRAINT fk_school_id FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- TOC entry 2645 (class 2606 OID 26382598)
-- Name: question_verification_criterions question_verification_criteries_questions_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY question_verification_criterions
    ADD CONSTRAINT question_verification_criteries_questions_fkey FOREIGN KEY (question_id) REFERENCES questions(id);


--
-- TOC entry 2644 (class 2606 OID 26382603)
-- Name: questions questions_template_variant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT questions_template_variant_id_fkey FOREIGN KEY (template_variant_id) REFERENCES template_variants(id);


--
-- TOC entry 2659 (class 2606 OID 26466042)
-- Name: registration_url registration_url_school_classes_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY registration_url
    ADD CONSTRAINT registration_url_school_classes_id_fk FOREIGN KEY (school_class_id) REFERENCES school_classes(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2658 (class 2606 OID 26466037)
-- Name: registration_url registration_url_schools_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY registration_url
    ADD CONSTRAINT registration_url_schools_id_fk FOREIGN KEY (school_id) REFERENCES schools(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2637 (class 2606 OID 26274126)
-- Name: school_classes school_classes_school_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY school_classes
    ADD CONSTRAINT school_classes_school_id_fkey FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- TOC entry 2638 (class 2606 OID 26464247)
-- Name: schools schools_city_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_city_id_fk FOREIGN KEY (city_id) REFERENCES city(id);


--
-- TOC entry 2639 (class 2606 OID 26274131)
-- Name: schools schools_school_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schools
    ADD CONSTRAINT schools_school_type_id_fkey FOREIGN KEY (school_type_id) REFERENCES school_types(id);


--
-- TOC entry 2636 (class 2606 OID 26274121)
-- Name: students students_school_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_school_class_id_fkey FOREIGN KEY (school_class_id) REFERENCES school_classes(id);


--
-- TOC entry 2634 (class 2606 OID 26273924)
-- Name: students students_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2635 (class 2606 OID 26274116)
-- Name: students students_user_id_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_user_id_fkey1 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2643 (class 2606 OID 26274152)
-- Name: teacher_subjects teacher_subjects_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- TOC entry 2642 (class 2606 OID 26274146)
-- Name: teacher_subjects teacher_subjects_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teacher_subjects
    ADD CONSTRAINT teacher_subjects_teacher_id_fkey FOREIGN KEY (teacher_id) REFERENCES teachers(id);


--
-- TOC entry 2641 (class 2606 OID 26274136)
-- Name: teachers teachers_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_id_fkey FOREIGN KEY (id) REFERENCES users(id);


--
-- TOC entry 2640 (class 2606 OID 26445423)
-- Name: teachers teachers_schools_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_schools_id_fk FOREIGN KEY (school_id) REFERENCES schools(id);


--
-- TOC entry 2656 (class 2606 OID 26382593)
-- Name: template_variants template; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY template_variants
    ADD CONSTRAINT template FOREIGN KEY (template_id) REFERENCES test_templates(id);


--
-- TOC entry 2646 (class 2606 OID 26274162)
-- Name: test_templates test_templates_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY test_templates
    ADD CONSTRAINT test_templates_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- TOC entry 2648 (class 2606 OID 26274172)
-- Name: tests tests_owner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES teachers(id);


--
-- TOC entry 2647 (class 2606 OID 26274167)
-- Name: tests tests_test_template_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tests
    ADD CONSTRAINT tests_test_template_id_fkey FOREIGN KEY (test_template_id) REFERENCES test_templates(id);


--
-- TOC entry 2655 (class 2606 OID 26274208)
-- Name: verification_pages verification_pages_verification_result_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_pages
    ADD CONSTRAINT verification_pages_verification_result_id_fkey FOREIGN KEY (verification_result_id) REFERENCES verification_results(id);


--
-- TOC entry 2654 (class 2606 OID 26274203)
-- Name: verification_results verification_results_verifier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_verifier_id_fkey FOREIGN KEY (verifier_id) REFERENCES teachers(id);


--
-- TOC entry 2653 (class 2606 OID 26274198)
-- Name: verification_results verification_results_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verification_results
    ADD CONSTRAINT verification_results_work_id_fkey FOREIGN KEY (work_id) REFERENCES works(id);


--
-- TOC entry 2652 (class 2606 OID 26274193)
-- Name: work_pages work_pages_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY work_pages
    ADD CONSTRAINT work_pages_work_id_fkey FOREIGN KEY (work_id) REFERENCES works(id);


--
-- TOC entry 2651 (class 2606 OID 26274187)
-- Name: works works_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_student_id_fkey FOREIGN KEY (student_id) REFERENCES students(id);


--
-- TOC entry 2649 (class 2606 OID 26274177)
-- Name: works works_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_test_id_fkey FOREIGN KEY (test_id) REFERENCES tests(id);


--
-- TOC entry 2650 (class 2606 OID 26274182)
-- Name: works works_verifier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY works
    ADD CONSTRAINT works_verifier_id_fkey FOREIGN KEY (verifier_id) REFERENCES teachers(id);


-- Completed on 2017-10-21 20:02:36 MSK

--
-- PostgreSQL database dump complete
--
