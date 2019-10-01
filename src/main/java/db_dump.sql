--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 10.10

-- Started on 2019-10-01 23:18:02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16402)
-- Name: characters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.characters (
    character_id integer NOT NULL,
    character_name text NOT NULL,
    character_attackpoint integer NOT NULL
);


ALTER TABLE public.characters OWNER TO postgres;

--
-- TOC entry 2791 (class 0 OID 16402)
-- Dependencies: 196
-- Data for Name: characters; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.characters (character_id, character_name, character_attackpoint) FROM stdin;
1	勇者	10
2	魔法使い	20
3	武闘家	30
4	怪人	40
5	神	50
\.


--
-- TOC entry 2669 (class 2606 OID 16409)
-- Name: characters characters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.characters
    ADD CONSTRAINT characters_pkey PRIMARY KEY (character_id);


-- Completed on 2019-10-01 23:18:02

--
-- PostgreSQL database dump complete
--

