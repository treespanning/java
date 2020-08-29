from selenium import webdriver
import unittest,time
import os,sys,csv
from ddt import ddt,data,unpack,file_data

from selenium.webdriver.common.keys import Keys


def getCsv(file_name):
    rows = []
    path = sys.path[0].replace('\ test','')

    with open(path + '/data/' + file_name, 'rt') as f:
        readers = csv.reader(f, delimiter=',', quotechar='|')
        next(readers, None)
        for row in readers:
            temprows = []
            for i in row:
                temprows.append(i)
            rows.append(temprows)
        return rows

@ddt
class blog(unittest.TestCase):
    def setUp(self):
        self.driver=webdriver.Chrome()
        self.driver.get("http://localhost:8080/servletblog/")
        self.driver.maximize_window()
    def tearDown(self):
        self.driver.quit()

    @data(*getCsv('text_blog_data.txt'))
   # @data(["测试11","测试22"],["测试33","测试44"],["测试55","测试66"])
    @unpack
    def test_Ainsert(self,value,expected_value):
        self.driver.find_element_by_id("article_add_btn").click()
        #print(self.driver.title)

        self.driver.implicitly_wait(5)
        # 句柄移动到新弹出的窗口
        self.driver.switch_to.window(self.driver.window_handles[1])
        #self.print(self.driver.title)
        self.driver.implicitly_wait(5)

        self.driver.find_element_by_id("article_title").send_keys(value)

        test_js='document.getElementById("ueditor_0").contentWindow.document.body.innerText="%s"'%(expected_value)
        self.driver.execute_script(test_js)
        self.driver.implicitly_wait(5)

        self.driver.find_element_by_id("btn_submit").click()
        time.sleep(8)
        #self.driver.switch_to_default.content()

    def test_Cdelete(self):
        inputs = self.driver.find_elements_by_tag_name("input")
        inputs[0].click()
        # for input in inputs:
        #    if inputs.get_attribute('type')=="checkbox":
        #       input.click()

        time.sleep(2)
        self.driver.find_element_by_id("article_delete_btn").click()

        # 貌似弹出来的不是alert直接定位就行
        #self.driver.find_element_by_xpath("//*[@id='delete_confirm']/div/div/div[3]/button[2]").send_keys(Keys.ENTER)  # 取消

        #time.sleep(8)

        #self.driver.find_element_by_id("article_delete_btn").send_keys(Keys.ENTER)
        time.sleep(2)
        self.driver.find_element_by_id("article_delete_submit").send_keys(Keys.ENTER)  # 确定
        time.sleep(8)
    def test_Bupdate(self):
        # 选择文章按钮
        divs = self.driver.find_elements_by_tag_name("input")
        divs[0].click()
        time.sleep(3)

        self.driver.find_element_by_link_text("修改").click()
        self.driver.implicitly_wait(3)
        print(self.driver.title)

        time.sleep(5)
        # 句柄移动到新弹出的窗口
        # driver.switch_to.window(driver.window_handles[1])
        handles = self.driver.window_handles
        # 此处数组是以1开始
        self.driver.switch_to.window(handles[2])

        time.sleep(5)
        print(self.driver.title)

        self.driver.find_element_by_id("article_title").clear()  #清除标题框中内容
        time.sleep(1)
        self.driver.find_element_by_id("article_title").send_keys("修改自动化测试")

        # 按清除按钮
        self.driver.find_element_by_id("btn_clear").click()
        time.sleep(1)
        # 文本编辑器，使用js实现
        test_js = 'document.getElementById("ueditor_0").contentWindow.document.body.innerText="%s"' % ("修改自动化测试111111")
        self.driver.execute_script(test_js)
        self.driver.implicitly_wait(5)

        self.driver.find_element_by_id("btn_submit").send_keys(Keys.ENTER)
        time.sleep(3)

if __name__=="__main__":
    unittest.main()