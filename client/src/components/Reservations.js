import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import ReservationOverview from "./ReservationOverview"

export default function Reservatios() {

    const [props, setProps] = useState(null);
    const {id} = useParams();
    console.log(id)

    useEffect(() => {
        const url = `http://localhost:8080/api/clients/${id}/reservations`;
        fetch(url)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error(`HTTP error ${response.status}`);
                }
            })
            .then(data => {
                setProps(data);
                console.log(data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [id]);

    if (!props) {
        return (
            <section>
                <p>Loading...</p>
            </section>
        );
      }

    return (
        <div className="reservation">
              {props.map((reservation) => {
                    return (
                        < ReservationOverview reservation={reservation} />
                    )
                })}
        </div>
    )
}