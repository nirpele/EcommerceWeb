
import { Product, Customer } from "./../Entitis.type";

export async function fetchCustomerIdByEmail(
  email: string
): Promise<number | undefined> {
  try {
    const data = await fetch(
      "http://localhost:8080/findMyIdByEmail?email=" + email,
      {
        headers: {
          "Content-Type": "application/json",
        },

        mode: "cors",
        method: "GET",
      }
    );
    const data_1 = await data.text();

    console.log(data_1);
    return parseInt(data_1);
  } catch (error) {
    console.log(error);
    return undefined;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function getAllProducts(): Promise<Product[] | undefined> {
  try {
    const response = await fetch("http://localhost:8080/products/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    });

    if (response.ok) {
      const data = await response.json();
      console.log("Request secses:", response.status);
      console.log(data);
      return data;
    } else {
      console.log("Request failed with status:", response.status);
      return undefined;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return undefined;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function getOneProduct(productId: number): Promise<Product | undefined> {
  try {
    const response = await fetch(
      "//localhost:8080/oneProduct?productId=" + productId,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
      }
    );

    if (response.ok) {
      const data = await response.json();
      console.log("Request secses:", response.status);
      console.log(data);
      return data;
    } else {
      console.log("Request failed with status:", response.status);
      return undefined;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return undefined;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function purchaseOneProduct(token: string,customerId: number, productId: number): Promise<number> {
  try {
    const response = await fetch(
      `http://localhost:8080/purchaseProduct?customerId=${customerId}&productId=${productId}`,
      {
        method: "POST",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
        mode: "cors",
      }
    );
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function addCustomer(customer: Customer): Promise<number> {
  try {
    const response = await fetch(`http://localhost:8080/customer/add`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(customer),
    });
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function getAllCustomerProducts(token:string,customerId:number): Promise<Product[] | undefined> {
  try {
    const response = await fetch("http://localhost:8080/customers/getAllProducts?customerId="+customerId, {
      method: "GET",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
    });
    if (response.ok) {
      const data = await response.json();
      console.log("Request secses:", response.status);
      console.log(data);
      return data;
    } else {
      console.log("Request failed with status:", response.status);
      return undefined;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return undefined;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function updateCustomer(token:string,customer: Customer): Promise<number> {
  try {
    const response = await fetch(`http://localhost:8080/customer/update`, {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(customer),
    });
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function deleteCustomer(token:string,customerId:number): Promise<number> {
  try {
    const response = await fetch(`http://localhost:8080/customer/delete?customerId=`+customerId, {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
    });
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function getAllCustomers(token:string): Promise<Customer[] | undefined> {
  try {
    const response = await fetch("http://localhost:8080/customers/all", {
      method: "GET",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
    });
    if (response.ok) {
      const data = await response.json();
      console.log("Request secses:", response.status);
      console.log(data);
      return data;
    } else {
      console.log("Request failed with status:", response.status);
      return undefined;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return undefined;
  }
}   

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

export async function addProduct(token:string,product: Product): Promise<number> {
  try {
    const response = await fetch(`http://localhost:8080/product/add`, {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(product),
    });
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//admin
export async function updateProduct(token:string,product: Product): Promise<number> {
  try {
    const response = await fetch(`http://localhost:8080/product/update`, {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(product),
    });
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//admin
export async function deleteProduct(token:string,productId: number): Promise<number> {
  try {
    const response = await fetch(`http://localhost:8080/product/delete?productId=`+productId, {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      mode: "cors",
    });
    if (response.ok) {
      console.log("Request successful");
      return 1;
    } else {
      console.log("Request failed with status:", response.status);
      return 2;
    }
  } catch (error) {
    console.log("An error occurred:", error);
    return 3;
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////