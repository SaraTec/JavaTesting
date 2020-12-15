import React from 'react'

export const Email = ({email, body, subj, id,  deleteById}) => {
    return (
        <div className="email">
            <div>{email}</div>
            <div>{body}</div>
            <div>{subj}</div>
            <button onClick = {()=>{deleteById(id)}}>DELETE</button>
        </div>
    )
}