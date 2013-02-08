#!/usr/bin/env python
#-*- encoding: utf-8 -*-
import urllib
import urllib2
import time
import Image
import ImageEnhance
import ImageFilter
import sys
import math
import random
from kmeans import kmeans
from kmeans import Point
from kmeans import Cluster
import copy

def extract(m, white=' ', blank='*', th=254):
    res = []
    col, row = m.size
    for x in range(row):
        res.append([])
        for y in range(col):
            if m.getpixel((y, x)) >= th:
                res[x].append(white)
            else:
                res[x].append(blank)
    return res


def pre_process(m):
    dd = extract(m)
    #print_sub_image(dd)
    #del_noise(dd)
    return dd


def print_sub_image(dd, start=0, end=None):
    if not end:
        end = len(dd[0])
    for x in dd:
        print ''.join(map(str, x[start: end]))


def main():
    urllib.urlretrieve("http://cas.baidu.com/?action=image&key=%s" % (int(time.time())), "./baidu.jpg")
    bbb()
    median_filter()


def median_filter():
    im = Image.open("./baidu.jpg")
    im = im.filter(ImageFilter.MedianFilter())
    enhancer = ImageEnhance.Contrast(im)
    im = enhancer.enhance(2)
    im = im.convert('1')
    im.show()
    print_sub_image(pre_process(im))


def aaa():
    im = Image.open('baidu.jpg')
    flagx = [0 for x in range(im.size[0])]
    pix = im.load()
    import pdb
    pdb.set_trace()
    #横坐标上的像素分布
    for x in range(im.size[0]):
        for y in range(im.size[1]):
            if pix[x, y][0] < 90:
                flagx[x] += 1
    print flagx
    result = []
    for i in range(im.size[0]):
        if flagx[i] > 0 and flagx[i - 1] <= 0:
            tmp = i  # 记录0->n的坐标
        if flagx[i] > 0 and flagx[i + 1] <= 0:
            #完成一个字符的横坐标扫描，针对这段用同样的方法扫描纵坐标
            flagy = [0 for x in range(im.size[1])]
            for y in range(im.size[1]):
                for x in range(i + 1)[tmp:]:
                    if pix[x, y][0] < 90:
                        flagy[y] += 1
            #用flagy记录纵坐标像素分布
            for j in range(im.size[1]):
                if flagy[j] > 0 and flagy[j - 1] <= 0:
                    ttmp = j  # 记录0->n的点
                if flagy[j] > 0 and flagy[j + 1] <= 0:
                    result.append((tmp, i, ttmp + 1, j + 1))
    print result
    for i, box in enumerate(result):
        (left, width, top, height) = box
        import pdb
        pdb.set_trace()
        im.crop((left, top, width, height)).save('%s.jpg' % i)

def bbb():
    points = []
    im = Image.open('baidu.jpg')
    for point in get_point(im):
        points.append(point)
    clusters = kmeans(points, 4, 0.000000000000000001)
    rrr = []
    import pdb
    pdb.set_trace()
    for x in range(im.size[1]):
        row = [" "] * im.size[0]
        rrr.append(row)
    for i,c in enumerate(clusters):
        ccc = copy.deepcopy(rrr)
        for p in c.points:
            ccc[p.coords[1]][p.coords[0]]="1"
        print "--------------------------------"
        print_sub_image(ccc)

def get_point(im):
    pix = im.load()
    for x in range(im.size[0]):
        for y in range(im.size[1]):
            if pix[x, y][0] < 90:
                yield Point([x, y])


if __name__ == '__main__':
    main()
