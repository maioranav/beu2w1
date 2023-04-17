--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7 (Ubuntu 14.7-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 15.2

-- Started on 2023-04-17 19:03:07

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16798)
-- Name: edifici; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.edifici (
    id bigint NOT NULL,
    citta character varying(255),
    indirizzo character varying(255),
    nome character varying(255) NOT NULL
);


ALTER TABLE public.edifici OWNER TO "EXW3L1";

--
-- TOC entry 209 (class 1259 OID 16797)
-- Name: edifici_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.edifici_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.edifici_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4006 (class 0 OID 0)
-- Dependencies: 209
-- Name: edifici_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.edifici_id_seq OWNED BY public.edifici.id;


--
-- TOC entry 212 (class 1259 OID 16807)
-- Name: postazioni; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.postazioni (
    id bigint NOT NULL,
    description character varying(255),
    maxp integer,
    type character varying(255),
    edificio_id bigint
);


ALTER TABLE public.postazioni OWNER TO "EXW3L1";

--
-- TOC entry 211 (class 1259 OID 16806)
-- Name: postazioni_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.postazioni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.postazioni_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4007 (class 0 OID 0)
-- Dependencies: 211
-- Name: postazioni_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.postazioni_id_seq OWNED BY public.postazioni.id;


--
-- TOC entry 214 (class 1259 OID 16816)
-- Name: prenotazioni; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.prenotazioni (
    id bigint NOT NULL,
    data date NOT NULL,
    postazione_id bigint,
    utente_id bigint
);


ALTER TABLE public.prenotazioni OWNER TO "EXW3L1";

--
-- TOC entry 213 (class 1259 OID 16815)
-- Name: prenotazioni_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.prenotazioni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prenotazioni_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4008 (class 0 OID 0)
-- Dependencies: 213
-- Name: prenotazioni_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.prenotazioni_id_seq OWNED BY public.prenotazioni.id;


--
-- TOC entry 216 (class 1259 OID 16823)
-- Name: utenti; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.utenti (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    fullname character varying(255),
    username character varying(255) NOT NULL
);


ALTER TABLE public.utenti OWNER TO "EXW3L1";

--
-- TOC entry 215 (class 1259 OID 16822)
-- Name: utenti_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.utenti_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utenti_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4009 (class 0 OID 0)
-- Dependencies: 215
-- Name: utenti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.utenti_id_seq OWNED BY public.utenti.id;


--
-- TOC entry 3834 (class 2604 OID 16801)
-- Name: edifici id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.edifici ALTER COLUMN id SET DEFAULT nextval('public.edifici_id_seq'::regclass);


--
-- TOC entry 3835 (class 2604 OID 16810)
-- Name: postazioni id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.postazioni ALTER COLUMN id SET DEFAULT nextval('public.postazioni_id_seq'::regclass);


--
-- TOC entry 3836 (class 2604 OID 16819)
-- Name: prenotazioni id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni ALTER COLUMN id SET DEFAULT nextval('public.prenotazioni_id_seq'::regclass);


--
-- TOC entry 3837 (class 2604 OID 16826)
-- Name: utenti id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti ALTER COLUMN id SET DEFAULT nextval('public.utenti_id_seq'::regclass);


--
-- TOC entry 3993 (class 0 OID 16798)
-- Dependencies: 210
-- Data for Name: edifici; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.edifici (id, citta, indirizzo, nome) FROM stdin;
1	Quarto Giorgio	Piazza Ruggiero 0, Piano 2	Dave McFly
2	Borgo Ursula	Strada Mazza 36, Piano 3	Goldie Wilson
3	Esposito ligure	Rotonda Ippolito 205	Jennifer Parker
4	Settimo Lauro	Contrada Gaetano 26	Match
5	Borgo Quasimodo	Contrada Grasso 272	Einstein
6	San Muzio del friuli	Contrada Rodolfo 57, Appartamento 24	Red The Bum
\.


--
-- TOC entry 3995 (class 0 OID 16807)
-- Dependencies: 212
-- Data for Name: postazioni; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.postazioni (id, description, maxp, type, edificio_id) FROM stdin;
1	Andromeda Tonks	4	Privato	5
2	Fawkes	5	Privato	1
3	Fawkes	23	Openspace	2
4	Peeves	90	SalaRiunioni	4
5	Dirk Cresswell	23	Openspace	1
6	Susan Bones	45	Openspace	6
7	Bob Ogden	79	SalaRiunioni	2
8	Newt Scamander	46	Openspace	1
9	Salazar Slytherin	44	Openspace	5
10	Rufus Scrimgeour	69	SalaRiunioni	3
\.


--
-- TOC entry 3997 (class 0 OID 16816)
-- Dependencies: 214
-- Data for Name: prenotazioni; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.prenotazioni (id, data, postazione_id, utente_id) FROM stdin;
1	2023-10-12	2	3
2	2021-02-12	5	3
3	2023-10-12	1	2
\.


--
-- TOC entry 3999 (class 0 OID 16823)
-- Dependencies: 216
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.utenti (id, email, fullname, username) FROM stdin;
1	lucia.gatti@esempio.it	Lucia Gatti	l.gatti
2	ferdinando.piras@esempio.it	Ferdinando Piras	f.piras
3	eustachio.pellegrino@esempio.it	Eustachio Pellegrino	e.pellegrino
4	odone.ferrara@esempio.it	Odone Ferrara	o.ferrara
\.


--
-- TOC entry 4010 (class 0 OID 0)
-- Dependencies: 209
-- Name: edifici_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.edifici_id_seq', 6, true);


--
-- TOC entry 4011 (class 0 OID 0)
-- Dependencies: 211
-- Name: postazioni_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.postazioni_id_seq', 10, true);


--
-- TOC entry 4012 (class 0 OID 0)
-- Dependencies: 213
-- Name: prenotazioni_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.prenotazioni_id_seq', 3, true);


--
-- TOC entry 4013 (class 0 OID 0)
-- Dependencies: 215
-- Name: utenti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.utenti_id_seq', 4, true);


--
-- TOC entry 3839 (class 2606 OID 16805)
-- Name: edifici edifici_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.edifici
    ADD CONSTRAINT edifici_pkey PRIMARY KEY (id);


--
-- TOC entry 3841 (class 2606 OID 16814)
-- Name: postazioni postazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.postazioni
    ADD CONSTRAINT postazioni_pkey PRIMARY KEY (id);


--
-- TOC entry 3843 (class 2606 OID 16821)
-- Name: prenotazioni prenotazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT prenotazioni_pkey PRIMARY KEY (id);


--
-- TOC entry 3845 (class 2606 OID 16832)
-- Name: utenti uk_9b90mk1nolf3ou90p42a93tjo; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT uk_9b90mk1nolf3ou90p42a93tjo UNIQUE (email);


--
-- TOC entry 3847 (class 2606 OID 16834)
-- Name: utenti uk_tn8mwk6h2wn28yyj7fco65gls; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT uk_tn8mwk6h2wn28yyj7fco65gls UNIQUE (username);


--
-- TOC entry 3849 (class 2606 OID 16830)
-- Name: utenti utenti_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utenti_pkey PRIMARY KEY (id);


--
-- TOC entry 3850 (class 2606 OID 16835)
-- Name: postazioni fk567dcy1xrko75goaugbwnfl8p; Type: FK CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.postazioni
    ADD CONSTRAINT fk567dcy1xrko75goaugbwnfl8p FOREIGN KEY (edificio_id) REFERENCES public.edifici(id);


--
-- TOC entry 3851 (class 2606 OID 16845)
-- Name: prenotazioni fk8kg9d6tr3jt4dmyxxwamn41r9; Type: FK CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk8kg9d6tr3jt4dmyxxwamn41r9 FOREIGN KEY (utente_id) REFERENCES public.utenti(id);


--
-- TOC entry 3852 (class 2606 OID 16840)
-- Name: prenotazioni fk8nhrjygc992dv2glaots97puk; Type: FK CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk8nhrjygc992dv2glaots97puk FOREIGN KEY (postazione_id) REFERENCES public.postazioni(id);


--
-- TOC entry 4005 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-04-17 19:03:07

--
-- PostgreSQL database dump complete
--

