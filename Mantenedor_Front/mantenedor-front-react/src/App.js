import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';

const urlObtenerDatos="http://localhost:8081/obtenerDatos";
const urlInsertar="http://localhost:8081/agregarDatos";
const urlActualizar="http://localhost:8081/actualizarDatos";
const urlEliminar="http://localhost:8081/eliminarDatos?id=";
const config = {
  headers: {
    "Content-type": "application/json; charset=UTF-8",
    "Accept": "application/json"
  }
}

const getCurrentDate=()=>{
  var dia = new Date().getDate();
  var mes = new Date().getMonth() + 1;
  var year = new Date().getFullYear();
  dia = validaFecha(dia);
  mes = validaFecha(mes);
  return dia + '-' + mes + '-' + year;
}

const validaFecha=(param)=>{
  var salida = "";
  if(param == 1 || param == 2 || param == 3 || param == 4 || param == 5 || param == 6 || param == 7 || param == 8 || param == 9){
    salida = "0"+param;
  }else{
    salida = param;
  }
  return salida;
}
  class App extends Component {
    state={
      data:[],
      modalInsertar: false,
      modalEliminar: false,
      modalError: false,
      form:{
        identificador: '',
        descripcion: '',
        fechaCreacion: '',
        vigente: '',
        tipoModal: ''
      }
    }

    peticionGetObtieneDatos=()=>{
      axios.get(urlObtenerDatos, config).then(response=> {
        console.log("Datos API: " + JSON.stringify(response.data));
        console.log("Datos API: " + JSON.stringify(response.data.datos));
        this.setState({data: response.data.datos});
      }).catch(error=>{
        console.log(error.message);
      })
    }

    peticionPostInsertar=async()=>{
      
      try {
        const body = {
          "identificador": '',
          "descripcion": this.state.form.descripcion,
          "fechaCreacion": getCurrentDate(),
          "vigente": this.state.form.vigente
          
         }

        if(null == this.state.form.descripcion || this.state.form.descripcion == "" || null == this.state.form.vigente || this.state.form.vigente == ""){
          this.setState({modalError: true});
        }else{
          await axios.post(urlInsertar, body).then(response=>{
            this.modalInsertar();
            this.peticionGetObtieneDatos();
          }).catch(error=>{
            console.log(error.message);
          })
        }
      } catch (error) {
        this.setState({modalError: true});
      }
    }

    peticionPostActualizar=async()=>{

      try {
          const body = {
              "identificador": this.state.form.identificador,
              "descripcion": this.state.form.descripcion,
              "fechaCreacion": getCurrentDate(),
              "vigente": this.state.form.vigente
          }

          if(null == this.state.form.descripcion || this.state.form.descripcion == "" || null == this.state.form.vigente || this.state.form.vigente == ""){
            this.setState({modalError: true});
          }else{
            await axios.post(urlActualizar, body).then(response=>{
              this.modalInsertar();
              this.peticionGetObtieneDatos();
            }).catch(error=>{
              console.log(error.message);
            })
          }
        } catch (error) {
          this.setState({modalError: true});
        }
    }

    seleccionarTarea=(tarea)=>{
      console.log("IDENTIFICADOR TAREA: " + tarea.identificador);
      console.log("DESCRIPCION TAREA: " + tarea.descripcion);
      console.log("FECHA TAREA: " + tarea.fechaCreacion);
      console.log("VIGENTE TAREA: " + tarea.vigente);
      this.setState({
        tipoModal: 'actualizar',
        form:{
          identificador: tarea.identificador,
          descripcion: tarea.descripcion,
          fechaCreacion: tarea.fechaCreacion,
          vigente: tarea.vigente
        }
      })
    }

    peticionEliminar=()=>{
      console.log("IDENTIFICADOR TAREA: " + this.state.form.identificador);
      axios.get(urlEliminar+this.state.form.identificador).then(response=>{
        this.setState({modalEliminar: false});
        this.peticionGetObtieneDatos();
      })
    }

    modalInsertar=()=>{
      this.setState({modalInsertar: !this.state.modalInsertar});
    }

    handleChange=async e=>{
      e.persist();
      await this.setState({
        form:{
          ...this.state.form,
          [e.target.name]: e.target.value
        }
      });
      console.log(this.state.form);
      }

    componentDidMount() {
      this.peticionGetObtieneDatos();
    }

    render(){
      const {form}=this.state;
    return (
    <div className="App">
      <br /><br /><br />
      <button className="btn btn-success" onClick={()=>{this.setState({form: null, tipoModal: 'insertar'}); this.modalInsertar()}} >Agregar Tarea</button>
      <br /><br />
        <table className="table ">
          <thead>
          <tr>
            <th>Identificador</th>
            <th>Descripción</th>
            <th>Fecha Creación</th>
            <th>Vigente</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
        {this.state.data.map(datos=>{
          return(
          <tr>
            <td> {datos.identificador} </td>
            <td> {datos.descripcion} </td>
            <td> {datos.fechaCreacion} </td>
            <td> {datos.vigente} </td>
            <td>
                  <button className="btn btn-primary" onClick={()=>{this.seleccionarTarea(datos); this.modalInsertar()}} ><FontAwesomeIcon icon={faEdit}/></button>
                  {"   "}
                  <button className="btn btn-danger" onClick={()=>{this.seleccionarTarea(datos); this.setState({modalEliminar: true})}} ><FontAwesomeIcon icon={faTrashAlt}/></button>
            </td>
          </tr>
            )
          })}
        </tbody>
        </table>

        <Modal isOpen={this.state.modalInsertar} >
                <ModalHeader style={{display: 'block'}}>
                  <span style={{float: 'right'}} >x</span>
                </ModalHeader>
                <ModalBody>
                  <div className="form-group">
                    <label htmlFor="id">Identificador</label>
                    <input className="form-control" type="text" name="identificador" id="identificador" readOnly onChange={this.handleChange} value={form?form.identificador: ''} />
                    <br />
                    <label htmlFor="nombre">Descripción</label>
                    <input className="form-control" type="text" name="descripcion" id="descripcion" onChange={this.handleChange} value={form?form.descripcion: ''} />
                    <br />
                    <label htmlFor="nombre">Fecha Creación</label>
                    <input className="form-control" type="text" name="fechaCreacion" id="fechaCreacion" readOnly onChange={this.handleChange} value={form?form.fechaCreacion: getCurrentDate()} />
                    <br />
                    <label htmlFor="capital_bursatil">Vigente (true/false)</label>
                    <input className="form-control" type="text" name="vigente" id="vigente" onChange={this.handleChange} value={form?form.vigente: ''} />
                  </div>
                </ModalBody>

                <ModalFooter>
                  {this.state.tipoModal=='insertar'?
                    <button className="btn btn-success" onClick={()=>this.peticionPostInsertar()} >
                    Insertar
                  </button>: <button className="btn btn-primary" onClick={()=>this.peticionPostActualizar()} >
                    Actualizar
                  </button>
                  }
                  <button className="btn btn-danger" onClick={()=>{this.modalInsertar()}} >Cancelar</button>
                </ModalFooter>
          </Modal>

          <Modal isOpen={this.state.modalEliminar}>
            <ModalBody>
               Estás seguro que deseas eliminar la tarea {form && form.descripcion}
            </ModalBody>
            <ModalFooter>
              <button className="btn btn-danger" onClick={()=>this.peticionEliminar()}>Sí</button>
              <button className="btn btn-secundary" onClick={()=>this.setState({modalEliminar: false})}>No</button>
            </ModalFooter>
          </Modal>


          <Modal isOpen={this.state.modalError}>
            <ModalBody>
               Favor completar campos vacios. Todos los campos son obligatorios
            </ModalBody>
            <ModalFooter>
              <button className="btn btn-secundary" onClick={()=>this.setState({modalError: false})}>OK</button>
            </ModalFooter>
          </Modal>

    </div>
  );
}
}

export default App;
