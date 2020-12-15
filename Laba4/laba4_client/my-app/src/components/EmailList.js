import React, {useState, useEffect} from 'react'
import { mockData } from '../mock.js'
import {Email} from './Email'

export const EmailList =()=>{
    const [loadedEmails, setEmails] = useState(null);

    const [mail, setMails] = useState("");
    const [subj, setSubj] = useState("");
    const [body, setBody] = useState("");

    const loadData = ()=>{
        fetch('http://localhost:8080/getAll')
        .then(response => response.json())
        .then(emails =>  setEmails(emails));
    }

    useEffect(() =>  {
        loadData();
        },[]);

    const deleteById=async(id)=>{
        await fetch('http://localhost:8080/deleteById',{
            method: 'POST', // или 'PUT'
            body: JSON.stringify({id:id}), // данные могут быть 'строкой' или {объектом}!
            headers: {
                'Content-Type': 'application/json'
            }
        });
        await loadData();
    }

    const findByEmail = async()=>{
        await fetch('http://localhost:8080/findByEmail',{
            method: 'POST',
            body: JSON.stringify({
                id:"",
                email:mail,
                subj:"",
                body:""
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
        .then(emails =>  setEmails(emails));
    }

    const findBySubj = async()=>{
        await fetch('http://localhost:8080/findBySubj',{
            method: 'POST', // или 'PUT'
            body: JSON.stringify({
                id:"",
                email:"",
                subj:subj,
                body:""
            }), // данные могут быть 'строкой' или {объектом}!
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
        .then(emails =>  setEmails(emails));
    }

    const addMessage=async()=>{
        await fetch('http://localhost:8080/add',{
            method: 'POST', // или 'PUT'
            body: JSON.stringify({
                id:"",
                email:mail,
                subj:subj,
                body:body
            }), // данные могут быть 'строкой' или {объектом}!
            headers: {
                'Content-Type': 'application/json'
            }
        });
        await loadData();
    }
    return (
        <>
            <div className="form">
                <div>
                    <label>Email</label>
                    <input value = {mail} onChange ={(e)=>{setMails(e.target.value)}} ></input>
                </div>
                <div>
                    <label>Subject</label>
                    <input value = {subj} onChange ={(e)=>{setSubj(e.target.value)}} ></input>
                </div>
                <div>
                    <label>Body</label>
                    <input value = {body} onChange ={(e)=>{ setBody(e.target.value)}} ></input>
                </div>
            </div>

            <div className="controleButtons">
                <button onClick={addMessage}>Send</button>
                <button onClick={findByEmail}>Filter by Email</button>
                <button onClick={findBySubj}>Filter by Subject</button>
            </div>
            
            {
                loadedEmails === null ? 'Завантаження...' :(
                <div className="emailList">
                    {loadedEmails.map((email)=>
                        <Email  deleteById = {deleteById} {...email}  />
                    )}
                </div>
                )
            }  
        </> 
    )
}