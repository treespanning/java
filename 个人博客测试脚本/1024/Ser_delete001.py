from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import unittest,time,re
driver=webdriver.Chrome()
driver.get("http://localhost:8080/servletblog/")
driver.implicitly_wait(5)
#driver.maximize_window()
print(driver.title)

inputs=driver.find_elements_by_id("article_list_div")
inputs[0].click()
#for input in inputs:
#    if inputs.get_attribute('type')=="checkbox":
 #       input.click()

time.sleep(2)
driver.find_element_by_id("article_delete_btn").click()

#貌似弹出来的不是alert直接定位就行
driver.find_element_by_xpath("//*[@id='delete_confirm']/div/div/div[3]/button[2]").send_keys(Keys.ENTER)#取消

time.sleep(8)

driver.find_element_by_id("article_delete_btn").send_keys(Keys.ENTER)
time.sleep(2)
driver.find_element_by_id("article_delete_submit").send_keys(Keys.ENTER)#确定

time.sleep(8)
driver.quit()
