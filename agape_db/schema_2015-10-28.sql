--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: agape; Type: SCHEMA; Schema: -; Owner: agape
--

CREATE SCHEMA agape;


ALTER SCHEMA agape OWNER TO agape;

SET search_path = agape, pg_catalog;

--
-- Name: add_answer_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_answer_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.answer_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.answer_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.answer_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_answer_history() OWNER TO agape;

--
-- Name: add_application_text_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_application_text_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.application_text_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.application_text_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.application_text_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_application_text_history() OWNER TO agape;

--
-- Name: add_course_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_course_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.course_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.course_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.course_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_course_history() OWNER TO agape;

--
-- Name: add_education_state_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_education_state_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.education_state_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.education_state_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.education_state_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_education_state_history() OWNER TO agape;

--
-- Name: add_lesson_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_lesson_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.lesson_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.lesson_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.lesson_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_lesson_history() OWNER TO agape;

--
-- Name: add_question_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_question_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.question_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.question_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.question_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_question_history() OWNER TO agape;

--
-- Name: add_role_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_role_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.role_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.role_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.role_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_role_history() OWNER TO agape;

--
-- Name: add_student_teacher_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_student_teacher_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.student_teacher_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.student_teacher_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.student_teacher_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_student_teacher_history() OWNER TO agape;

--
-- Name: add_user_history(); Type: FUNCTION; Schema: agape; Owner: agape
--

CREATE FUNCTION add_user_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF (TG_OP = 'UPDATE') THEN
            INSERT INTO agape.user_history  SELECT now(), 'Update',  NEW.*;
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO agape.user_history  SELECT now(),'Delete', OLD.*;
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO agape.user_history  SELECT now(),'Insert', NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL; 
    END;
$$;


ALTER FUNCTION agape.add_user_history() OWNER TO agape;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: activation_token; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE activation_token (
    token character varying(200) NOT NULL,
    user_id bigint
);


ALTER TABLE agape.activation_token OWNER TO agape;

--
-- Name: answer; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE answer (
    id bigint NOT NULL,
    content character varying(2000),
    modyfication_date timestamp without time zone NOT NULL,
    question_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE agape.answer OWNER TO agape;

--
-- Name: answer_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE answer_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    content character varying(2000),
    modyfication_date timestamp without time zone NOT NULL,
    question_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE agape.answer_history OWNER TO agape;

--
-- Name: application_text; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE application_text (
    id character varying(200) NOT NULL,
    domain character varying(100),
    text text,
    type character varying(5)
);


ALTER TABLE agape.application_text OWNER TO agape;

--
-- Name: application_text_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE application_text_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id character varying(200) NOT NULL,
    domain character varying(100),
    text text,
    type character varying(5)
);


ALTER TABLE agape.application_text_history OWNER TO agape;

--
-- Name: course; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE course (
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    description character varying(5000),
    enabled boolean,
    image oid,
    modification_date timestamp without time zone NOT NULL,
    number bigint NOT NULL,
    status character varying(3) NOT NULL,
    title character varying(200) NOT NULL,
    modification_user_id bigint
);


ALTER TABLE agape.course OWNER TO agape;

--
-- Name: course_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE course_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    description character varying(5000),
    enabled boolean,
    image oid,
    modification_date timestamp without time zone NOT NULL,
    number bigint NOT NULL,
    status character varying(3) NOT NULL,
    title character varying(200) NOT NULL,
    modification_user_id bigint
);


ALTER TABLE agape.course_history OWNER TO agape;

--
-- Name: course_privileges; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE course_privileges (
    id bigint NOT NULL,
    modification_date timestamp without time zone NOT NULL,
    modificationoperator character varying(30),
    course_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE agape.course_privileges OWNER TO agape;

--
-- Name: education_state; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE education_state (
    id bigint NOT NULL,
    checked_date timestamp without time zone,
    comment character varying(2000),
    read_comment_date timestamp without time zone,
    read_date timestamp without time zone,
    sent_date timestamp without time zone,
    shared_date timestamp without time zone,
    lesson_id bigint NOT NULL,
    student_id bigint NOT NULL,
    modification_date timestamp without time zone,
    modification_user_id bigint
);


ALTER TABLE agape.education_state OWNER TO agape;

--
-- Name: education_state_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE education_state_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    checked_date timestamp without time zone,
    comment character varying(2000),
    read_comment_date timestamp without time zone,
    read_date timestamp without time zone,
    sent_date timestamp without time zone,
    shared_date timestamp without time zone,
    lesson_id bigint NOT NULL,
    student_id bigint NOT NULL,
    modification_date timestamp without time zone,
    modification_user_id bigint
);


ALTER TABLE agape.education_state_history OWNER TO agape;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: agape; Owner: agape
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE agape.hibernate_sequence OWNER TO agape;

--
-- Name: lesson; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE lesson (
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    description character varying(500),
    enabled boolean NOT NULL,
    homework text,
    introduction character varying(2000),
    modification_date timestamp without time zone NOT NULL,
    number bigint NOT NULL,
    status character varying(3) NOT NULL,
    course_id bigint NOT NULL,
    modification_user_id bigint
);


ALTER TABLE agape.lesson OWNER TO agape;

--
-- Name: lesson_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE lesson_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    description character varying(500),
    enabled boolean NOT NULL,
    homework text,
    introduction character varying(2000),
    modification_date timestamp without time zone NOT NULL,
    number bigint NOT NULL,
    status character varying(3) NOT NULL,
    course_id bigint NOT NULL,
    modification_user_id bigint
);


ALTER TABLE agape.lesson_history OWNER TO agape;

--
-- Name: member; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE member (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(25) NOT NULL,
    phone_number character varying(12) NOT NULL
);


ALTER TABLE agape.member OWNER TO agape;

--
-- Name: news; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE news (
    id bigint NOT NULL,
    content character varying(500) NOT NULL,
    modification_date timestamp without time zone,
    modification_user_id bigint,
    title character varying(100) NOT NULL
);


ALTER TABLE agape.news OWNER TO agape;

--
-- Name: question; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE question (
    id bigint NOT NULL,
    content text,
    creation_date timestamp without time zone NOT NULL,
    enabled boolean NOT NULL,
    modification_date timestamp without time zone NOT NULL,
    number bigint NOT NULL,
    status character varying(3) NOT NULL,
    lesson_id bigint NOT NULL,
    modification_user_id bigint
);


ALTER TABLE agape.question OWNER TO agape;

--
-- Name: question_addition; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE question_addition (
    id bigint NOT NULL,
    modification_date timestamp without time zone,
    modification_user_id bigint,
    content oid,
    enabled boolean NOT NULL,
    filen_name character varying(200) NOT NULL,
    file_type character varying(255),
    height bigint,
    mime_type character varying(20),
    number bigint NOT NULL,
    question_id bigint NOT NULL,
    description character varying(200)
);


ALTER TABLE agape.question_addition OWNER TO agape;

--
-- Name: question_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE question_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    content text,
    creation_date timestamp without time zone NOT NULL,
    enabled boolean NOT NULL,
    modification_date timestamp without time zone NOT NULL,
    number bigint NOT NULL,
    status character varying(3) NOT NULL,
    lesson_id bigint NOT NULL,
    modification_user_id bigint
);


ALTER TABLE agape.question_history OWNER TO agape;

--
-- Name: role; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE role (
    id bigint NOT NULL,
    modification_date timestamp without time zone,
    role_name character varying(20) NOT NULL,
    user_id bigint NOT NULL,
    enabled boolean
);


ALTER TABLE agape.role OWNER TO agape;

--
-- Name: role_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE role_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    modification_date timestamp without time zone,
    role_name character varying(20) NOT NULL,
    user_id bigint NOT NULL,
    enabled boolean
);


ALTER TABLE agape.role_history OWNER TO agape;

--
-- Name: student_teacher; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE student_teacher (
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    current character varying(3) NOT NULL,
    deleted_date timestamp without time zone,
    student_id bigint NOT NULL,
    teacher_id bigint NOT NULL
);


ALTER TABLE agape.student_teacher OWNER TO agape;

--
-- Name: student_teacher_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE student_teacher_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    current character varying(3) NOT NULL,
    deleted_date timestamp without time zone,
    student_id bigint NOT NULL,
    teacher_id bigint NOT NULL
);


ALTER TABLE agape.student_teacher_history OWNER TO agape;

--
-- Name: user; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE "user" (
    id bigint NOT NULL,
    address character varying(50),
    birth_date timestamp without time zone,
    code character varying(10),
    community character varying(50),
    country character varying(100),
    email character varying(30) NOT NULL,
    name character varying(30) NOT NULL,
    password character varying(100),
    place character varying(100),
    province character varying(100),
    registration_date timestamp without time zone,
    religion character varying(50),
    sex character varying(10) NOT NULL,
    status character varying(3) NOT NULL,
    surname character varying(50),
    mail_confirmation boolean,
    send_mail boolean
);


ALTER TABLE agape."user" OWNER TO agape;

--
-- Name: user_history; Type: TABLE; Schema: agape; Owner: agape; Tablespace: 
--

CREATE TABLE user_history (
    "timestamp" timestamp without time zone NOT NULL,
    action character varying(50),
    id bigint NOT NULL,
    address character varying(50),
    birth_date timestamp without time zone,
    code character varying(10),
    community character varying(50),
    country character varying(100),
    email character varying(30) NOT NULL,
    name character varying(30) NOT NULL,
    password character varying(100),
    place character varying(100),
    province character varying(100),
    registration_date timestamp without time zone,
    religion character varying(50),
    sex character varying(10) NOT NULL,
    status character varying(3) NOT NULL,
    surname character varying(50),
    mail_confirmation boolean,
    send_mail boolean
);


ALTER TABLE agape.user_history OWNER TO agape;

--
-- Name: activation_token_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY activation_token
    ADD CONSTRAINT activation_token_pkey PRIMARY KEY (token);


--
-- Name: answer_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- Name: application_text_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY application_text
    ADD CONSTRAINT application_text_pkey PRIMARY KEY (id);


--
-- Name: course_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: course_privileges_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY course_privileges
    ADD CONSTRAINT course_privileges_pkey PRIMARY KEY (id);


--
-- Name: education_state_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY education_state
    ADD CONSTRAINT education_state_pkey PRIMARY KEY (id);


--
-- Name: lesson_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY lesson
    ADD CONSTRAINT lesson_pkey PRIMARY KEY (id);


--
-- Name: member_email_key; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY member
    ADD CONSTRAINT member_email_key UNIQUE (email);


--
-- Name: member_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- Name: news_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: question_addition_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY question_addition
    ADD CONSTRAINT question_addition_pkey PRIMARY KEY (id);


--
-- Name: question_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: role_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: student_lesson; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY education_state
    ADD CONSTRAINT student_lesson UNIQUE (lesson_id, student_id);


--
-- Name: student_teacher_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY student_teacher
    ADD CONSTRAINT student_teacher_pkey PRIMARY KEY (id);


--
-- Name: uniqueemail; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT uniqueemail UNIQUE (email);


--
-- Name: user_email_key; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_email_key UNIQUE (email);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: agape; Owner: agape; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: answer_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER answer_history AFTER INSERT OR DELETE OR UPDATE ON answer FOR EACH ROW EXECUTE PROCEDURE add_answer_history();


--
-- Name: application_text_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER application_text_history AFTER INSERT OR DELETE OR UPDATE ON application_text FOR EACH ROW EXECUTE PROCEDURE add_application_text_history();


--
-- Name: course_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER course_history AFTER INSERT OR DELETE OR UPDATE ON course FOR EACH ROW EXECUTE PROCEDURE add_course_history();


--
-- Name: education_state_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER education_state_history AFTER INSERT OR DELETE OR UPDATE ON education_state FOR EACH ROW EXECUTE PROCEDURE add_education_state_history();


--
-- Name: lesson_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER lesson_history AFTER INSERT OR DELETE OR UPDATE ON lesson FOR EACH ROW EXECUTE PROCEDURE add_lesson_history();


--
-- Name: question_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER question_history AFTER INSERT OR DELETE OR UPDATE ON question FOR EACH ROW EXECUTE PROCEDURE add_question_history();


--
-- Name: role_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER role_history AFTER INSERT OR DELETE OR UPDATE ON role FOR EACH ROW EXECUTE PROCEDURE add_role_history();


--
-- Name: student_teacher_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER student_teacher_history AFTER INSERT OR DELETE OR UPDATE ON student_teacher FOR EACH ROW EXECUTE PROCEDURE add_student_teacher_history();


--
-- Name: user_history; Type: TRIGGER; Schema: agape; Owner: agape
--

CREATE TRIGGER user_history AFTER INSERT OR DELETE OR UPDATE ON "user" FOR EACH ROW EXECUTE PROCEDURE add_user_history();


--
-- Name: fk1b14b3353dd34485; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY question_addition
    ADD CONSTRAINT fk1b14b3353dd34485 FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fk338ad3d3231842; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY news
    ADD CONSTRAINT fk338ad3d3231842 FOREIGN KEY (modification_user_id) REFERENCES "user"(id);


--
-- Name: fk358076f80efbe5; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY role
    ADD CONSTRAINT fk358076f80efbe5 FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- Name: fk400313a229c9205; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY education_state
    ADD CONSTRAINT fk400313a229c9205 FOREIGN KEY (lesson_id) REFERENCES lesson(id);


--
-- Name: fk400313ac34a8f75; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY education_state
    ADD CONSTRAINT fk400313ac34a8f75 FOREIGN KEY (student_id) REFERENCES "user"(id);


--
-- Name: fk735d33be3dd34485; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk735d33be3dd34485 FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fk735d33bec34a8f75; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk735d33bec34a8f75 FOREIGN KEY (student_id) REFERENCES "user"(id);


--
-- Name: fkb22fc686547e8325; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY course_privileges
    ADD CONSTRAINT fkb22fc686547e8325 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fkb22fc686f80efbe5; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY course_privileges
    ADD CONSTRAINT fkb22fc686f80efbe5 FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- Name: fkba823be6229c9205; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY question
    ADD CONSTRAINT fkba823be6229c9205 FOREIGN KEY (lesson_id) REFERENCES lesson(id);


--
-- Name: fkbe10ad38547e8325; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY lesson
    ADD CONSTRAINT fkbe10ad38547e8325 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fkd4e389deb9b2906e; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY student_teacher
    ADD CONSTRAINT fkd4e389deb9b2906e FOREIGN KEY (teacher_id) REFERENCES "user"(id);


--
-- Name: fkd4e389dec34a8f75; Type: FK CONSTRAINT; Schema: agape; Owner: agape
--

ALTER TABLE ONLY student_teacher
    ADD CONSTRAINT fkd4e389dec34a8f75 FOREIGN KEY (student_id) REFERENCES "user"(id);


--
-- PostgreSQL database dump complete
--

