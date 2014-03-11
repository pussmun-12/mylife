/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
window.echo = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "Echo", "echo", [str,window.outerWidth,300]);
};

var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicity call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
        alert('working!');
        /*  window.echo("echome", function(echoValue) {
         //alert(echoValue); // should alert true.
         var keys = Object.keys(echoValue);
         var first = true;
         //	alert(keys);
         //		var keys = ['content://media/external/images/media/2760'];
         var appy = document.getElementById('car');
         for(var key in keys){
         var val = keys[key];
         if(val.indexOf('/DCIM/100ANDRO') >= 0){


         var div = document.createElement('div');
         var el = document.createElement('img');
         var text = document.createTextNode(val);

         //	el.src = 'file:/' + val;
         //    el.src = 'http://phaseoneimageprofessor.files.wordpress.com/2013/07/iqpw29_main_image_.jpg';
         //   el.src ='http://upload.wikimedia.org/wikipedia/commons/0/0f/2010-02-19_3000x2000_chicago_skyline.jpg';
         if(first && [val] != 90){
         el.onload = function(){
         //  alert('nu');
         //	$( ".carousel" ).carousel();

         }
         first = false;
         }
         var imgobj = echoValue[val];
         var data = 'data:image/jpeg;base64,' + imgobj.url;
         el.src = data;
         var pad = 300/imgobj.width;//float
         var padding = pad * imgobj.height;
         if(imgobj.rotate === 90){
         el.className = 'rotate';
         div.className = 'divvy';
         el.style.marginLeft = ((window.outerWidth - Math.round(padding)) / 2)+ 'px';
         div.appendChild(el);
         }

         //	div.appendChild(text);
         // div.style.width = '100%';

         //alert(el.src);
         appy.appendChild(div);

         }
         }
         $( ".carousel" ).carousel();
         });    */


    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        /*var parentElement = document.getElementById(id);
         var listeningElement = parentElement.querySelector('.listening');
         var receivedElement = parentElement.querySelector('.received');

         listeningElement.setAttribute('style', 'display:none;');
         receivedElement.setAttribute('style', 'display:block;');

         console.log('Received Event: ' + id);
         */
    }
};


