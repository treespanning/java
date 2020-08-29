# coding:utf-8
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
driver.maximize_window()
driver.find_element_by_id("article_add_btn").click()
print(driver.title)


driver.implicitly_wait(5)
#句柄移动到新弹出的窗口
driver.switch_to.window(driver.window_handles[1])
print(driver.title)
driver.implicitly_wait(5)

driver.find_element_by_id("article_title").send_keys("自动化测试")

#test_js='document.getElementById("ueditor_0").contentWindow.document.body.innerText="%s"'%("测试切换frame")
#driver.execute_script(test_js)

#driver.implicitly_wait(5)

driver.find_element_by_id("btn_submit").click()
time.sleep(8)
driver.quit()

# driver.switch_to.frame("ueditor_0")
# driver.find_element_by_class_name("view").send_keys("请求")
# driver.switch_to_default.content()
# driver.find_element_by_id("btn_submit").click()
# time.sleep(10)





