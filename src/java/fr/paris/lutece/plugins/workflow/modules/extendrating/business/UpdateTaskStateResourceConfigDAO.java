/*
 * Copyright (c) 2002-2022, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.extendrating.business;

import fr.paris.lutece.plugins.workflow.modules.extendrating.util.Constants;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfigDAO;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * 
 * UpdateTaskStateResourceConfigDAO
 *
 */
public class UpdateTaskStateResourceConfigDAO implements ITaskConfigDAO<UpdateTaskStateResourceConfig>
{

    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = " SELECT id_task, id_state_start, id_state_end, nb_vote, operation"
            + " FROM task_extend_rating_update_resource_state_cf  WHERE id_task = ? ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO task_extend_rating_update_resource_state_cf( id_task, id_state_start, id_state_end, nb_vote, operation )"
            + " VALUES ( ?,?,?,?,? ) ";
    private static final String SQL_QUERY_UPDATE = "UPDATE task_extend_rating_update_resource_state_cf SET id_state_start = ?, id_state_end = ?, nb_vote = ?, operation = ? "
            + " WHERE id_task = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM task_extend_rating_update_resource_state_cf WHERE id_task = ? ";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( UpdateTaskStateResourceConfig config )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Constants.getPlugin( ) ) )
        {
            int nIndex = 1;

            daoUtil.setInt( nIndex++, config.getIdTask( ) );
            daoUtil.setInt( nIndex++, config.getStartState( ) );
            daoUtil.setInt( nIndex++, config.getEndState( ) );
            daoUtil.setInt( nIndex++, config.getNbVote( ) );
            daoUtil.setInt( nIndex++, config.getOperation( ) );

            daoUtil.executeUpdate( );
            daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( UpdateTaskStateResourceConfig config )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, Constants.getPlugin( ) ) )
        {
            int nIndex = 1;

            daoUtil.setInt( nIndex++, config.getStartState( ) );
            daoUtil.setInt( nIndex++, config.getEndState( ) );
            daoUtil.setInt( nIndex++, config.getNbVote( ) );
            daoUtil.setInt( nIndex++, config.getOperation( ) );

            daoUtil.setInt( nIndex++, config.getIdTask( ) );
            daoUtil.executeUpdate( );
            daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UpdateTaskStateResourceConfig load( int nIdTask )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_PRIMARY_KEY, Constants.getPlugin( ) ) )
        {
            UpdateTaskStateResourceConfig config = null;

            daoUtil.setInt( 1, nIdTask );

            daoUtil.executeQuery( );

            int nIndex = 1;

            if ( daoUtil.next( ) )
            {
                config = new UpdateTaskStateResourceConfig( );
                config.setIdTask( daoUtil.getInt( nIndex++ ) );
                config.setStartState( daoUtil.getInt( nIndex++ ) );
                config.setEndState( daoUtil.getInt( nIndex++ ) );
                config.setNbVote( daoUtil.getInt( nIndex++ ) );
                config.setOperation( daoUtil.getInt( nIndex++ ) );
            }

            daoUtil.free( );

            return config;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdTask )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, Constants.getPlugin( ) ) )
        {
            daoUtil.setInt( 1, nIdTask );
            daoUtil.executeUpdate( );
            daoUtil.free( );
        }
    }

}
