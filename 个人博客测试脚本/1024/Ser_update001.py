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
print(driver.title)

#选择文章按钮
divs=driver.find_elements_by_tag_name("input")
divs[0].click()
time.sleep(3)


driver.find_element_by_link_text("修改").click()
driver.implicitly_wait(3)
print(driver.title)

time.sleep(5)
#句柄移动到新弹出的窗口
#driver.switch_to.window(driver.window_handles[1])
handles = driver.window_handles
#此处数组是以1开始
driver.switch_to.window(handles[2])

time.sleep(5)
print(driver.title)

driver.find_element_by_id("article_title").clear()#清除标题框中内容

driver.find_element_by_id("article_title").send_keys("修改自动化测试")

#按清除按钮
driver.find_element_by_id("btn_clear").click()
time.sleep(3)
#文本编辑器，使用js实现
test_js='document.getElementById("ueditor_0").contentWindow.document.body.innerText="%s"'%("修改自动化测试111111")
driver.execute_script(test_js)
time.sleep(4)

driver.find_element_by_id("btn_submit").send_keys(Keys.ENTER)
time.sleep(3)
driver.quit()

