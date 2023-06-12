import React from 'react';

import {useState} from "react";



export default function LogoutClient() {

    sessionStorage.removeItem('token');
    sessionStorage.removeItem('clientId');
    window.location.href = `/api/clients/register`;
    
}
