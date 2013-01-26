#!/usr/bin/env python


def addslashes(s):
        d = {'"': '\\"', "'": "\\'", "\0": "\\\0", "\\": "\\\\"}
        return ''.join(d.get(c, c) for c in s)


def uni_str(a, encoding="gbk"):
    if isinstance(a, (list, tuple)):
        s = []
        for i, k in enumerate(a):
            s.append(uni_str(k, encoding))
        return s
    elif isinstance(a, dict):
        s = {}
        for i, k in enumerate(a.items()):
            key, value = k
            s[uni_str(key, encoding)] = uni_str(value, encoding)
        return s
    elif isinstance(a, unicode):
        return a.encode(encoding)
    elif isinstance(a, str) or (hasattr(a, '__str__') and callable(getattr(a, '__str__'))):
        if getattr(a, '__str__'):
            a = str(a)
        return unicode(a, encoding)
    else:
        return a


def main():
    pass

if __name__ == '__main__':
    main()
