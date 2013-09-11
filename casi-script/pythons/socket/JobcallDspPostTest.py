'''
Created on Jul 11, 2013

@author: jiebao
'''

import socket
import json
import traceback
import random
i=random.Random().randint(1,100000)
id="kevin_%s"%i
postURL = "http://10.224.82.73:1984/task"
postData = {"callbackURL": "http://nsz2nbrwss.qa.webex.com/nbr/CallbackServlet?action=standardNBRReady",
            "isTPMeeting": "false", "jobID": id,
            "monitorJobID": id,
            "nbrDataList": [{
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_1378380084.conf",
                            "type": "NBRconf"},
                            {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_6_0_1378380084.cad",
                            "type": "Docshow cad file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_6_0_1378380084.cai",
                            "type": "Docshow cai file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_6_100_1378380084.dat",
                            "type": "Docshow dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_6_100_1378380084.idx",
                            "type": "Docshow idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_115_1378380535.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_115_1378380535.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_117_1378380715.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_117_1378380715.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_119_1378380851.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_119_1378380851.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_121_1378381000.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_121_1378381000.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_123_1378381093.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_123_1378381093.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_125_1378381181.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_125_1378381181.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_127_1378381253.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_127_1378381253.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_129_1378381296.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_129_1378381296.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_131_1378381307.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_131_1378381307.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_133_1378381329.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_133_1378381329.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_135_1378381413.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_135_1378381413.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_137_1378381506.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_137_1378381506.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_139_1378381519.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_139_1378381519.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_141_1378381530.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_141_1378381530.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_143_1378381575.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_143_1378381575.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_145_1378381618.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_145_1378381618.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_149_1378381920.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_149_1378381920.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_153_1378382236.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_153_1378382236.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_155_1378382248.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_155_1378382248.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_157_1378382260.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_157_1378382260.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_159_1378382517.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_159_1378382517.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_161_1378382612.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_161_1378382612.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_163_1378382757.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_163_1378382757.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_165_1378382877.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_165_1378382877.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_169_1378384489.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_169_1378384489.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_183_1378386934.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_183_1378386934.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_185_1378386977.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_185_1378386977.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_577_1378387556.dat",
                            "type": "Share dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxcbr_10.224.82.103_4278243307_10.224.82.103_1378380084_4_577_1378387556.idx",
                            "type": "Share idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/Wbxcbr_tel_4278243307_182473319_1378380084_5_144465800_1378380085260.wav",
                            "type": "TeleAgent"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_21_268435457_1378380084.dat",
                            "type": "Video dat file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_21_268435457_1378380084.idx",
                            "type": "Video idx file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_51_268435459_1378383562.dat",
                            "type": "StreamVideo dat"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_51_268435459_1378383562.idx",
                            "type": "StreamVideo idx"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_51_268435459_1378387147.dat",
                            "type": "StreamVideo dat"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_51_268435459_1378387147.idx",
                            "type": "StreamVideo idx"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_51_268435459_1378387616.dat",
                            "type": "StreamVideo dat"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingData/wbxmcsr_10.224.82.99_4278243307_182473319_1378380084_51_268435459_1378387616.idx",
                            "type": "StreamVideo idx"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingMis/485550787_4278243307201396_recordingXML_1.xml",
                            "type": "Static file"}, {
                            "path": "/export/playback/700137087/787/485550787/307/4278243307/RecordingPac/4278243307_1378380084000_1.arf",
                            "type": "ARF File"}],
            "outputPath": "/export/playback/700137087/787/485550787/307/4278243307/RecordingMP4/977601127",
            "recordID": "977601127", "siteID": "700137087"}

import urllib2

def httpclient(timeout, url, data):
    socket.setdefaulttimeout(int(timeout))
    fd = None
    try:
        fd = urllib2.urlopen(url=url, data=data)
        code, body = fd.getcode(), fd.read()
        return code, body
    except Exception:
        print traceback.format_exc()
        return (500, "No")
    finally:
        fd and fd.close()
print httpclient(timeout=10, url=postURL,data=json.dumps(postData))


   