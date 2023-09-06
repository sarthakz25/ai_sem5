/* laptop info */
laptop('Asus ROG Zephyrus G14', 'Asus', 14, 'AMD Ryzen 9', 16, '1TB SSD', 'Windows 11').
laptop('MacBook Pro 16', 'Apple', 16, 'Apple M2 Pro', 32, '1TB SSD', 'macOS').
laptop('Dell XPS 13', 'Dell', 13.3, 'Intel Core i7', 16, '512GB SSD', 'Windows 11').
laptop('Acer Predator Helios 300', 'Acer', 15.6, 'Intel Core i9', 16, '1TB SSD', 'Windows 11').
laptop('Lenovo ThinkPad X1 Carbon', 'Lenovo', 14, 'Intel Core i5', 16, '512GB SSD', 'Linux').

/* price info */
laptop_price('Asus ROG Zephyrus G14', 1800).
laptop_price('MacBook Pro 16', 2500).
laptop_price('Dell XPS 13', 1300).
laptop_price('Acer Predator Helios 300', 1500).
laptop_price('Lenovo ThinkPad X1 Carbon', 1600).

/* color info */
laptop_color('Asus ROG Zephyrus G14', 'Moonlight White').
laptop_color('MacBook Pro 16', 'Space Gray').
laptop_color('Dell XPS 13', 'Silver').
laptop_color('Acer Predator Helios 300', 'Black').
laptop_color('Lenovo ThinkPad X1 Carbon', 'Black').

/* gpu info */
laptop_gpu('Asus ROG Zephyrus G14', 'AMD Radeon RX 6700S').
laptop_gpu('MacBook Pro 16', 'AMD Radeon Pro 5500M').
laptop_gpu('Dell XPS 13', 'Intel UHD Graphics').
laptop_gpu('Acer Predator Helios 300', 'NVIDIA GeForce RTX 3070 Ti').
laptop_gpu('Lenovo ThinkPad X1 Carbon', 'Intel Iris Xe Graphics').

/* dedicated gpus */
dedicated_gpu('AMD Radeon RX 6700S').
dedicated_gpu('AMD Radeon Pro 5500M').
dedicated_gpu('NVIDIA GeForce RTX 3070 Ti').

/* to check if laptop is within budget */
is_within_budget(Model, Budget) :-
    laptop_price(Model, Price),
    Price =< Budget.

/* to check if laptop is powerful */
is_powerful_laptop(Model) :-
    laptop(Model, _, _, Processor, Ram, _, _),
    (Processor = 'Intel Core i9'; Processor = 'AMD Ryzen 9'; Processor = 'Apple M2 Pro'),
    Ram >= 16.

/* to check if laptop has a dedicated gpu */
has_dedicated_gpu(Model) :-
    laptop(Model, _, _, _, _, _, _),
    laptop_gpu(Model, GPU),
    dedicated_gpu(GPU).
