# -*- coding: utf-8 -*-
import unittest
import os
import sys
import time
import HTMLTestRunner

# 手工添加案例到套件，
def createsuite():

 discovers = unittest.defaultTestLoader.discover('../1024', pattern='blog.py', top_level_dir=None)
 #第一个参数为项目中想要运行的包，第二个是想运行的集合（试试单一个类可以不）
 print( discovers)
 return discovers
if __name__ == "__main__":
    print("==============================")
    curpath = sys.path[0]
    print(sys.path[0])
    if not os.path.exists(curpath + '/resultreport'):
        os.makedirs(curpath + '/resultreport')
    # 取当前时间
    #对时间格式化time.strftime("格式化形式",time.localtime<本地时间>(time.time()<获取时间戳>))
    #为了生成的HTML报告名字不重复，引入时间戳的概念
    #时间戳——————time.time()
    #将时间戳转化为本地的一个时间time.local.time(time.time())
    #再将本地时间以"%Y-%m-%d-%H %M %S"的形式输出
    now = time.strftime("%Y-%m-%d-%H %M %S", time.localtime(time.time()))
    print(time.localtime(time.time()))
    print("===================")
    print(now)
    #经过上述步骤，已经得到了HTML报告的名称
    filename = curpath + '/resultreport/' + now + 'resultreport.html'
    #打开HTML文件，wb以写的方式
    with open(filename, 'wb') as fp:
        # 出html报告，括号里的参数是HTML报告里的参数
        runner = HTMLTestRunner.HTMLTestRunner(stream=fp, title=u'测试报告',
                                               description=u'用例执行情况',verbosity=2)

        suite = createsuite()
        runner.run(suite)