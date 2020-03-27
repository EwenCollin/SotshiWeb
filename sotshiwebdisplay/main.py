from selenium import webdriver

DRIVER = 'chromedriver'

url = 'https://google.fr'

width = 1024
height = 512

driver = webdriver.Chrome(DRIVER)

driver.get(url)
driver.set_window_size(width, height)

driver.save_screenshot("image.png")