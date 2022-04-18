-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: db
-- Thời gian đã tạo: Th4 14, 2022 lúc 04:19 AM
-- Phiên bản máy phục vụ: 8.0.27
-- Phiên bản PHP: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quoc_timekeep`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permit`
--

CREATE TABLE `permit` (
  `id` int NOT NULL,
  `id_time_keep` int DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `time_in` datetime(6) DEFAULT NULL,
  `time_out` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `permit`
--

INSERT INTO `permit` (`id`, `id_time_keep`, `reason`, `status`, `note`, `time_in`, `time_out`) VALUES
(3, 10, 'OT java', 0, NULL, NULL, NULL),
(4, 10, 'string', 1, 'string', '2022-04-12 13:00:44.000000', '2022-04-12 18:00:44.000000'),
(5, 1, '123', 0, NULL, '2022-04-09 01:39:00.000000', '2022-04-09 01:39:00.000000'),
(6, 2, 'asasa', 0, NULL, '2022-04-13 01:39:00.000000', '2022-04-14 01:39:00.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `position`
--

CREATE TABLE `position` (
  `id` int NOT NULL,
  `name_position` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `position`
--

INSERT INTO `position` (`id`, `name_position`, `role`) VALUES
(1, 'Trưởng Phòng', 1),
(2, 'Nhân Viên', 2),
(3, 'Lao công', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `id` int NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `id_position` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_time_work` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`id`, `adress`, `email`, `gender`, `id_position`, `name`, `id_time_work`) VALUES
(1, 'Giao Thủy', 'quocdbph17660@fpt.edu.vn', b'1', 1, 'Đặng Bảo Quốc', 1),
(2, 'Nam Định', 'dangquoc0704@gmail.com', b'1', 2, 'Bảo Quốc', 1),
(3, 'Hà Nội', 'test@gmail.com', b'1', 2, 'QuocHaNoi', 1),
(14, 'Vinh Phuc', 'quocq3187@gmail.com', b'1', 1, 'Quốc3', 2),
(31, 'Nam ĐỊnh', 'test01@gmail.com', b'1', 1, 'quoccctesst4', 2),
(32, 'Lao Cai', 'quoctest3@gmail.com', b'1', 1, 'quoccctesst3', 3),
(33, 'Nam Dinh', 'quoctest01@gmail.com', b'1', 1, 'quoccctesst3', 2),
(34, 'Vinh PHuc', 'Mai@gmail.com', b'0', 3, 'Ngoc Mai', 2),
(35, 'Vinh Phuc', 'hong@gmail.com', b'1', 1, 'Bich Hong', 1),
(36, 'Thanh Hóa', 'hao@gmail.com', b'1', 1, 'Quỳnh Hảo', 1),
(38, 'fpoly', 'dangquoc07044@gmail.com', b'1', 1, 'phongTt', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `time_keep`
--

CREATE TABLE `time_keep` (
  `id` int NOT NULL,
  `id_staff` int DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `start_time_approved` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `stop_time` datetime DEFAULT NULL,
  `stop_time_approved` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `time_keep`
--

INSERT INTO `time_keep` (`id`, `id_staff`, `start_time`, `start_time_approved`, `status`, `stop_time`, `stop_time_approved`) VALUES
(1, 1, '2022-04-11 08:42:01', '2022-04-11 08:42:01', b'1', '2022-04-11 18:42:01', '2022-04-11 18:42:01'),
(2, 2, '2022-03-11 13:37:26', '2022-03-11 13:37:26', b'1', '2022-03-11 18:00:01', '2022-03-11 18:00:01'),
(5, 3, '2022-04-11 13:42:12', '2022-04-11 13:42:12', b'1', '2022-04-11 13:42:37', '2022-04-11 13:42:37'),
(6, 14, '2022-04-11 08:40:28', '2022-04-11 08:40:28', b'1', '2022-04-11 18:40:28', '2022-04-11 18:40:28'),
(7, 1, '2022-04-12 11:46:37', '2022-04-12 11:46:37', b'1', '2022-04-12 18:37:26', '2022-04-12 18:37:26'),
(10, 3, '2022-04-12 13:52:44', '2022-04-12 13:52:44', b'0', NULL, NULL),
(14, 2, '2022-04-12 23:20:38', '2022-04-12 23:20:38', b'1', '2022-04-12 18:00:00', '1970-01-01 18:00:00'),
(21, 2, '2022-04-13 19:30:07', '2022-04-13 19:30:07', b'1', '2022-04-13 19:30:22', '2022-04-13 19:30:22');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `time_work`
--

CREATE TABLE `time_work` (
  `id` int NOT NULL,
  `start_time` time DEFAULT NULL,
  `status` int DEFAULT NULL,
  `stop_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `time_work`
--

INSERT INTO `time_work` (`id`, `start_time`, `status`, `stop_time`) VALUES
(1, '08:00:00', 1, '18:00:00'),
(2, '08:00:00', 2, '12:00:00'),
(3, '13:00:00', 3, '17:00:00');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `permit`
--
ALTER TABLE `permit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_time_keep` (`id_time_keep`);

--
-- Chỉ mục cho bảng `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_position` (`id_position`),
  ADD KEY `id_time_work` (`id_time_work`);

--
-- Chỉ mục cho bảng `time_keep`
--
ALTER TABLE `time_keep`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_staff` (`id_staff`);

--
-- Chỉ mục cho bảng `time_work`
--
ALTER TABLE `time_work`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `permit`
--
ALTER TABLE `permit`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `position`
--
ALTER TABLE `position`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `time_keep`
--
ALTER TABLE `time_keep`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `time_work`
--
ALTER TABLE `time_work`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `permit`
--
ALTER TABLE `permit`
  ADD CONSTRAINT `permit_ibfk_1` FOREIGN KEY (`id_time_keep`) REFERENCES `time_keep` (`id`);

--
-- Các ràng buộc cho bảng `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`id_position`) REFERENCES `position` (`id`),
  ADD CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`id_time_work`) REFERENCES `time_work` (`id`);

--
-- Các ràng buộc cho bảng `time_keep`
--
ALTER TABLE `time_keep`
  ADD CONSTRAINT `time_keep_ibfk_1` FOREIGN KEY (`id_staff`) REFERENCES `staff` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
