-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 29 jan. 2021 à 19:28
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lokyerpbase`
--

-- --------------------------------------------------------

--
-- Structure de la table `adress`
--

CREATE TABLE `adress` (
  `id` int(11) NOT NULL,
  `numRue` int(11) NOT NULL,
  `libelleRue` varchar(20) NOT NULL,
  `nomVille` varchar(20) NOT NULL,
  `codepostal` int(11) NOT NULL,
  `gouvernorat` varchar(20) NOT NULL,
  `pays` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `adress`
--

INSERT INTO `adress` (`id`, `numRue`, `libelleRue`, `nomVille`, `codepostal`, `gouvernorat`, `pays`) VALUES
(31, 7, '7', 'Monastir', 7, '7', 'Tunisie'),
(32, 12, 'cite el omrane', 'Monastir', 5000, 'monastir', 'Tunisie'),
(40, 500, 'Bourgiba', 'Monastir', 5000, 'monastir', 'Tunisie'),
(43, 5, 'sfqsf', 'Monastir', 6000, 'monastir', 'Tunisie'),
(45, 545, '54', 'Sousse', 464, '464', 'Tunisie'),
(46, 32, 'fi doura', 'Monastir', 5000, 'monastir', 'Tunisie'),
(47, 4, 'fi doura', 'Monastir', 5000, 'monastir', 'Tunisie'),
(49, 77, '77', 'Monastir', 77, '77', 'Tunisie'),
(50, 77, '77', 'Monastir', 77, '777', 'Tunisie'),
(51, 25, 'el omrane', 'Monastir', 5000, 'Monastir', 'Tunisie'),
(52, 12, 'liberte', 'Monastir', 5000, 'monastir', 'Tunisie'),
(53, 15, 'habib', 'Monastir', 5000, 'monastir', 'Tunisie'),
(54, 6, 'cite el nasrr', 'Monastir', 5000, 'monastir', 'Tunisie'),
(55, 41, 'cité el omrane', 'Monastir', 5000, 'monastir', 'Tunisie'),
(56, 12, 'staah jabeur', 'Monastir', 5000, 'monastir', 'Tunisie'),
(57, 2, 'polytech', 'Monastir', 5000, 'monastir', 'Tunisie'),
(58, 2, 'polytech', 'Monastir', 5000, 'monastir', 'Tunisie'),
(59, 45, 'polu', 'Monastir', 5000, 'monastir', 'Tunisie'),
(60, 12, 'poly', 'Monastir', 5000, 'monastir', 'Tunisie'),
(61, 1, 'liberte', 'Monastir', 5000, 'monastir', 'Tunisie'),
(62, 12, 'liberte', 'Monastir', 5000, 'monastir', 'Tunisie'),
(63, 12, 'liberte', 'Monastir', 5000, 'monastir', 'Tunisie');

-- --------------------------------------------------------

--
-- Structure de la table `bon_livraison`
--

CREATE TABLE `bon_livraison` (
  `id` int(11) NOT NULL,
  `code` varchar(13) NOT NULL,
  `client` int(11) NOT NULL,
  `date` timestamp NULL DEFAULT current_timestamp(),
  `address` int(11) NOT NULL,
  `information` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bon_livraison`
--

INSERT INTO `bon_livraison` (`id`, `code`, `client`, `date`, `address`, `information`) VALUES
(4, 'f51ba9b8-5f00', 27, '2021-01-25 11:32:08', 55, '446888DD'),
(5, '12216d61-5fc5', 27, '2021-01-26 10:55:58', 60, '77DFRR'),
(6, 'dac59bbd-5fc6', 27, '2021-01-26 11:08:44', 61, '777DFF'),
(7, '348130a2-5fdc', 27, '2021-01-26 13:41:34', 63, 'FFRRTT');

--
-- Déclencheurs `bon_livraison`
--
DELIMITER $$
CREATE TRIGGER `codeuiidbon` BEFORE INSERT ON `bon_livraison` FOR EACH ROW SET NEW.code = UUID()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  `telfix` varchar(8) NOT NULL,
  `telmobile` varchar(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  `website` varchar(50) DEFAULT NULL,
  `raision_social` int(11) NOT NULL,
  `compte_bancaire` int(11) NOT NULL,
  `address` int(11) NOT NULL,
  `tva_ajussti` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `matricule`, `type`, `description`, `telfix`, `telmobile`, `email`, `website`, `raision_social`, `compte_bancaire`, `address`, `tva_ajussti`) VALUES
(27, '06940586', 'PHYSIQUE', 'med', '55555555', '77777777', 'zidiak1@gmail.com', 'www.java.com', 28, 28, 32, 0),
(37, '47854/PG/448/06', 'MORALE', 'qsdqsddq', '77787979', '77787979', 'momo@gmail.com', 'www.momo.com', 39, 39, 43, 1),
(40, '06940586', 'PHYSIQUE', 'medecin', '55555557', '77777779', 'zidiak1@gmail.com', 'www.java.com', 47, 47, 54, 0),
(41, '12885678/D/G/F/123', 'PHYSIQUE', 'bon client', '55777889', '55987987', 'mohamed@gmail.com', 'www.mohamedcorp.com', 48, 48, 56, 1),
(42, '12345678/D/F/C/123', 'PHYSIQUE', 'test', '55777777', '9958485', 'zidiak1@gmail.com', 'www.ahmed.com', 52, 52, 62, 0);

-- --------------------------------------------------------

--
-- Structure de la table `compte_bancaire`
--

CREATE TABLE `compte_bancaire` (
  `id` int(11) NOT NULL,
  `nom_banque` varchar(50) NOT NULL,
  `nom_agance` varchar(50) NOT NULL,
  `rib_num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `compte_bancaire`
--

INSERT INTO `compte_bancaire` (`id`, `nom_banque`, `nom_agance`, `rib_num`) VALUES
(28, 'dar', 'dar', 1254566884),
(36, 'STB', 'STB', 46464),
(39, 'qsdq', 'qsdq', 545),
(41, '646', '646', 545),
(42, 'tijari', 'tijari', 777846446),
(43, 'tijari', 'tijari', 7777),
(45, '77', '77', 77),
(46, '77', '77', 77),
(47, 'tijari', 'tijari', 1545454545),
(48, 'STB', 'STB', 1545458547),
(49, 'tijari', 'tijari', 21345678),
(50, 'tijari', 'tijari', 21345678),
(51, 'tijari', 'tijari', 45664),
(52, 'STB', 'STB', 1215458785);

-- --------------------------------------------------------

--
-- Structure de la table `devis`
--

CREATE TABLE `devis` (
  `id` int(11) NOT NULL,
  `code` varchar(13) NOT NULL,
  `client` int(11) NOT NULL,
  `date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `devis`
--

INSERT INTO `devis` (`id`, `code`, `client`, `date`) VALUES
(24, 'f12954c2', 27, '2021-01-20 12:06:35'),
(25, 'a24939be', 27, '2021-01-20 12:11:32'),
(26, '94dcdb78', 27, '2021-01-20 12:25:28'),
(27, '6a5dfde8', 27, '2021-01-20 12:31:27'),
(28, 'd1a50b22', 27, '2021-01-20 12:34:20'),
(29, 'c1efbef0', 37, '2021-01-21 11:21:07'),
(30, 'd9d811d5', 37, '2021-01-21 11:21:48'),
(31, 'f6ffe3ba', 37, '2021-01-21 11:22:36'),
(32, '4b7dad69e', 37, '2021-01-21 11:24:58'),
(33, '2ade72b6', 37, '2021-01-21 11:38:22'),
(34, '28ba1311-5bdf', 37, '2021-01-21 11:52:38'),
(35, '5ece447f-5be0', 27, '2021-01-21 12:01:18'),
(36, 'cd3135dd-5be2', 27, '2021-01-21 12:18:42'),
(37, '242061f4-5be3', 27, '2021-01-21 12:21:08'),
(38, '48ea6422-5be4', 27, '2021-01-21 12:29:19'),
(39, '97f00f23-5be7', 27, '2021-01-21 12:53:00'),
(41, '56d95398-5e81', 27, '2021-01-24 20:18:36'),
(42, '21f37046-5fb3', 27, '2021-01-26 08:47:33'),
(43, 'f2eef247-5fc0', 27, '2021-01-26 10:26:27'),
(44, '056fa02f-5fc2', 27, '2021-01-26 10:34:08'),
(45, '46179c37-5fc2', 27, '2021-01-26 10:35:56'),
(46, 'e630ea5a-5fc4', 27, '2021-01-26 10:54:44'),
(47, 'f1f433d4-5fdb', 27, '2021-01-26 13:39:42');

--
-- Déclencheurs `devis`
--
DELIMITER $$
CREATE TRIGGER `insert_guid` BEFORE INSERT ON `devis` FOR EACH ROW SET NEW.code = UUID()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `id` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  `telfix` varchar(8) NOT NULL,
  `telmobile` varchar(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  `website` varchar(50) NOT NULL,
  `raision_social` int(11) NOT NULL,
  `compte_bancaire` int(11) NOT NULL,
  `address` int(11) NOT NULL,
  `tva_ajussti` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`id`, `matricule`, `type`, `description`, `telfix`, `telmobile`, `email`, `website`, `raision_social`, `compte_bancaire`, `address`, `tva_ajussti`) VALUES
(8, '444qsd44qsd4', 'PHYSIQUE', 'vendre', '777777', '777777', 'java@gmail.com', 'java.com', 51, 51, 59, 1);

-- --------------------------------------------------------

--
-- Structure de la table `facture_client`
--

CREATE TABLE `facture_client` (
  `id` int(11) NOT NULL,
  `code` varchar(13) DEFAULT NULL,
  `mode_payment` varchar(20) NOT NULL,
  `client` int(11) NOT NULL,
  `date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déclencheurs `facture_client`
--
DELIMITER $$
CREATE TRIGGER `uuidcodefacture` BEFORE INSERT ON `facture_client` FOR EACH ROW SET NEW.code = UUID()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  `telfix` varchar(8) NOT NULL,
  `telmobile` varchar(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  `website` varchar(50) NOT NULL,
  `raision_social` int(11) NOT NULL,
  `compte_bancaire` int(11) NOT NULL,
  `address` int(11) NOT NULL,
  `tva_ajussti` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id`, `matricule`, `type`, `description`, `telfix`, `telmobile`, `email`, `website`, `raision_social`, `compte_bancaire`, `address`, `tva_ajussti`) VALUES
(1, '7777', 'MORALE', 'guten tag', '7474747', '7777474', 'draxlmaier@gmail.com', 'www.draxlmaier.com', 36, 36, 40, 0),
(2, '45646', 'PHYSIQUE', '646	6464	64', '6464', '64464', '64646', '6466', 41, 41, 45, 1);

-- --------------------------------------------------------

--
-- Structure de la table `lignes_bon_livraison`
--

CREATE TABLE `lignes_bon_livraison` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalHt` double NOT NULL,
  `totalTva` double NOT NULL,
  `produit` int(11) NOT NULL,
  `bon_livraison` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lignes_bon_livraison`
--

INSERT INTO `lignes_bon_livraison` (`id`, `quantity`, `totalHt`, `totalTva`, `produit`, `bon_livraison`) VALUES
(4, 10, 700, 826, 4, 4),
(5, 10, 30, 35.4, 3, 5),
(6, 3, 9, 10.620000000000001, 3, 6),
(7, 50, 150, 177, 3, 7);

--
-- Déclencheurs `lignes_bon_livraison`
--
DELIMITER $$
CREATE TRIGGER `maj_stock_trig` BEFORE INSERT ON `lignes_bon_livraison` FOR EACH ROW UPDATE produit 
SET produit.stock = produit.stock -NEW.quantity
WHERE produit.id = NEW.produit
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `lignes_devis`
--

CREATE TABLE `lignes_devis` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalHt` double NOT NULL,
  `totalTva` double NOT NULL,
  `produit` int(11) NOT NULL,
  `devis` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lignes_devis`
--

INSERT INTO `lignes_devis` (`id`, `quantity`, `totalHt`, `totalTva`, `produit`, `devis`) VALUES
(22, 44, 132, 155.76, 3, 25),
(23, 20, 60, 70.8, 3, 25),
(24, 10, 30, 35.4, 3, 34),
(25, 10, 8000, 9440, 1, 34),
(26, 4, 3200, 3776, 1, 35),
(27, 6, 4800, 5664, 1, 35),
(28, 15, 45, 53.1, 3, 36),
(29, 2, 6, 7.08, 3, 37),
(30, 5, 15, 17.7, 3, 37),
(31, 11, 33, 38.94, 3, 38),
(32, 5, 15, 17.7, 3, 38),
(33, 9, 27, 31.86, 3, 39),
(37, 7, 21, 24.78, 3, 42),
(38, 20, 60, 70.8, 3, 43),
(39, 50, 150, 177, 3, 45),
(40, 4, 12, 14.16, 3, 46),
(41, 4, 12, 14.16, 3, 47);

-- --------------------------------------------------------

--
-- Structure de la table `lignes_facture`
--

CREATE TABLE `lignes_facture` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalHt` double NOT NULL,
  `totalTva` double NOT NULL,
  `produit` int(11) NOT NULL,
  `facture_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `product_family`
--

CREATE TABLE `product_family` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `type` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `product_family`
--

INSERT INTO `product_family` (`id`, `nom`, `type`) VALUES
(1, 'metale', 'accessoir voiture'),
(2, 'qsdqsd', 'sdqd'),
(3, 'manger', 'legume'),
(4, 'table', 'immobilier'),
(5, 'metal', 'utilitaire'),
(6, 'agroalimentaire', 'fruit'),
(7, 'table', 'table');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `ref` varchar(20) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `unit` varchar(15) NOT NULL,
  `unit_price` double NOT NULL,
  `unit_price_tva` double NOT NULL,
  `min_stock` double NOT NULL,
  `stock` double NOT NULL,
  `fournisseur` int(11) NOT NULL,
  `famille` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `ref`, `designation`, `unit`, `unit_price`, `unit_price_tva`, `min_stock`, `stock`, `fournisseur`, `famille`) VALUES
(1, 'RF/77R/8884', 'porte voiture', 'MILLIGRAMME', 800, 944, 10, 40, 1, 1),
(3, '11', 'batata', 'MILLIGRAMME', 3, 3.54, 20, 37, 2, 3),
(4, 'TNTAB4587', 'Table Bleu', 'MILLIGRAMME', 70, 82.6, 30, 490, 1, 4),
(5, 'PR/R18/120', 'ecrou', 'MILLIGRAMME', 8, 9.44, 10, 145, 1, 5),
(6, '08795/AA/FR', 'fraise', 'MILLIGRAMME', 2.5, 2.95, 45, 5000, 1, 6),
(7, '12345/FC/TC', 'table', 'MILLIGRAMME', 2, 2.36, 30, 150, 1, 7);

-- --------------------------------------------------------

--
-- Structure de la table `raison_social`
--

CREATE TABLE `raison_social` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `sexe` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `raison_social`
--

INSERT INTO `raison_social` (`id`, `nom`, `prenom`, `sexe`) VALUES
(28, 'gharbi', 'ahmed', 'homme'),
(36, 'draxlmaier', 'mets', 'homme'),
(39, 'mohamed', 'momo', 'homme'),
(41, '6464', '64646', 'homme'),
(42, 'Arnaque', 'ohh', 'homme'),
(43, 'Arnaque', 'qq', 'homme'),
(45, '77', '77', 'homme'),
(46, '77', '77', 'homme'),
(47, 'salah', 'ali', 'homme'),
(48, 'mohamed', 'salah', 'homme'),
(49, 'Cyber', 'Ahmed', 'homme'),
(50, 'Cyber', 'Ahmed', 'homme'),
(51, 'CYBER', 'AHMED', 'homme'),
(52, 'amira', 'gharbi', 'homme');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `account_created` timestamp NOT NULL DEFAULT current_timestamp(),
  `etat` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `login`, `password`, `account_created`, `etat`) VALUES
(1, 'ahmed', 'gharbi', 'zidiak1@gmail.com', 'ahmed', '2020-12-24 10:27:28', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adress`
--
ALTER TABLE `adress`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_livraison`
--
ALTER TABLE `bon_livraison`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bon_livraison_client` (`client`),
  ADD KEY `fk_bon_livraison_adress` (`address`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_client_adress` (`address`),
  ADD KEY `fk_client_compte_bancaire` (`compte_bancaire`),
  ADD KEY `fk_client_raison_social` (`raision_social`);

--
-- Index pour la table `compte_bancaire`
--
ALTER TABLE `compte_bancaire`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `devis`
--
ALTER TABLE `devis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_devis_client` (`client`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_entreprise_social` (`raision_social`),
  ADD KEY `fk_entreprise_bancaire` (`compte_bancaire`),
  ADD KEY `fk_entreprise_address` (`address`);

--
-- Index pour la table `facture_client`
--
ALTER TABLE `facture_client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_facture_client_client` (`client`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_founisseur_address` (`address`),
  ADD KEY `fk_founisseur_banque` (`compte_bancaire`),
  ADD KEY `fk_founisseur_social` (`raision_social`);

--
-- Index pour la table `lignes_bon_livraison`
--
ALTER TABLE `lignes_bon_livraison`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ligne_bon_livraison_produit` (`produit`),
  ADD KEY `fk_ligne_bon_livraison_bon_livraison` (`bon_livraison`);

--
-- Index pour la table `lignes_devis`
--
ALTER TABLE `lignes_devis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ligne_devis_produit` (`produit`),
  ADD KEY `fk_ligne_devis_devis` (`devis`);

--
-- Index pour la table `lignes_facture`
--
ALTER TABLE `lignes_facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_facture_client_af` (`facture_client`),
  ADD KEY `fk_facture_produit_ligne` (`produit`);

--
-- Index pour la table `product_family`
--
ALTER TABLE `product_family`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `raison_social`
--
ALTER TABLE `raison_social`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adress`
--
ALTER TABLE `adress`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT pour la table `bon_livraison`
--
ALTER TABLE `bon_livraison`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `compte_bancaire`
--
ALTER TABLE `compte_bancaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `devis`
--
ALTER TABLE `devis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `facture_client`
--
ALTER TABLE `facture_client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `lignes_bon_livraison`
--
ALTER TABLE `lignes_bon_livraison`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `lignes_devis`
--
ALTER TABLE `lignes_devis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `lignes_facture`
--
ALTER TABLE `lignes_facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `product_family`
--
ALTER TABLE `product_family`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `raison_social`
--
ALTER TABLE `raison_social`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bon_livraison`
--
ALTER TABLE `bon_livraison`
  ADD CONSTRAINT `fk_bon_livraison_adress` FOREIGN KEY (`address`) REFERENCES `adress` (`id`),
  ADD CONSTRAINT `fk_bon_livraison_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `fk_client_raison_social` FOREIGN KEY (`raision_social`) REFERENCES `raison_social` (`id`);

--
-- Contraintes pour la table `devis`
--
ALTER TABLE `devis`
  ADD CONSTRAINT `fk_devis_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD CONSTRAINT `fk_entreprise_address` FOREIGN KEY (`address`) REFERENCES `adress` (`id`),
  ADD CONSTRAINT `fk_entreprise_bancaire` FOREIGN KEY (`compte_bancaire`) REFERENCES `compte_bancaire` (`id`),
  ADD CONSTRAINT `fk_entreprise_social` FOREIGN KEY (`raision_social`) REFERENCES `raison_social` (`id`);

--
-- Contraintes pour la table `facture_client`
--
ALTER TABLE `facture_client`
  ADD CONSTRAINT `fk_facture_client_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD CONSTRAINT `fk_founisseur_social` FOREIGN KEY (`raision_social`) REFERENCES `raison_social` (`id`);

--
-- Contraintes pour la table `lignes_bon_livraison`
--
ALTER TABLE `lignes_bon_livraison`
  ADD CONSTRAINT `fk_ligne_bon_livraison_bon_livraison` FOREIGN KEY (`bon_livraison`) REFERENCES `bon_livraison` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ligne_bon_livraison_produit` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `lignes_devis`
--
ALTER TABLE `lignes_devis`
  ADD CONSTRAINT `fk_ligne_devis_devis` FOREIGN KEY (`devis`) REFERENCES `devis` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ligne_devis_produit` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `lignes_facture`
--
ALTER TABLE `lignes_facture`
  ADD CONSTRAINT `fk_facture_client_af` FOREIGN KEY (`facture_client`) REFERENCES `facture_client` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_facture_produit_ligne` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
