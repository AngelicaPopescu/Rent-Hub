import React from 'react';
import { MDBFooter } from 'mdb-react-ui-kit';


export default function Footer() {
    return (
        <div style={{ width:'100%', bottom:'0', height: 'auto', display: 'flex', flexDirection: 'column' , position:'fixed  '}}>

            <MDBFooter className='bg-light text-center text-white' id='footer' style={{ marginTop: 'auto' }}>

                <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
                    © 2023 Copyright:
                    <a className='text-white' href='http://www.codecool.com'>
                        CodeCool.com
                    </a>
                </div>
            </MDBFooter>
        </div>
    );
}
