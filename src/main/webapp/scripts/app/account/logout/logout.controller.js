'use strict';

angular.module('firstappApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
