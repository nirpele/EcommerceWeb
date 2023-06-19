import React, { useState } from 'react'
import { Customer } from '../Entitis.type';
import { addCustomer } from '../Service/Service';
import { useNavigate } from 'react-router-dom';

function Register() {
  const navigate = useNavigate();
  const [customer, setCustomer] = useState<Customer>({
    id: undefined,
    firstName: "",
    lastName: "",
    email: "",
    password: ""
  });
  const handleRegister = async () => {
    
      const resultCustomerRegister = await addCustomer(customer)
      if (resultCustomerRegister === 1) {
        alert("hello " + customer.firstName + "you Register successfully!");
        setTimeout(() => {
          navigate(-1);
        }, 2000);
      }
      if(resultCustomerRegister === 2) {
        alert("add customer failed!");
      }
      if(resultCustomerRegister === 3) {
        alert("add customer failed!");
      }

    ;
  }



  return (
    <div>

      <form>
        <label>First Name: </label>
        <input type="name" value={customer.firstName} onChange={(event) => setCustomer((prev: any) => ({ ...prev, firstName: event.target.value }))} /><br />
        <label>Last Name: </label>
        <input type="name" value={customer.lastName} onChange={(event) => setCustomer((prev: any) => ({ ...prev, lastName: event.target.value }))} /><br />
        <label>Email: </label>
        <input type="email" value={customer.email} onChange={(event) => setCustomer((prev: any) => ({ ...prev, email: event.target.value }))} /><br />
        <label>Password: </label>
        <input type="password" value={customer.password} onChange={(event) => setCustomer((prev: any) => ({ ...prev, password: event.target.value }))} /><br />
        <button type="button" onClick={handleRegister}>add customer</button>
      </form>
    </div>
  )
}

export default Register